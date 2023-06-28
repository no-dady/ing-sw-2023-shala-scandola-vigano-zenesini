package server.controller;

import client.network.State;
import observer.Observer;
import server.controller.actions.ColumnSelectAction;
import server.controller.actions.TileSelectAction;
import server.exceptions.IllegalPlayersNumberException;
import server.model.*;
import setup.ConfigsFromJson;
import util.Messages.Message;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

import server.controller.actions.Action;
import util.Messages.StateMessage;
import util.Parser;

/**
 * The type Game controller.
 */
public class GameController implements Observer<Action>{

    /**
     * The constant game.
     */
    protected  Game game;
    /**
     * The Lobby.
     */
    protected Lobby lobby;


    /**
     * Instantiates a new Game controller.
     */
    public GameController( Lobby lobby ) {
        this.lobby = lobby;
    }

    /**
     * Create lobby.
     *
     * @param playerNumber the player number
     * @throws IllegalPlayersNumberException the illegal players number exception
     */
    public void createLobby(int playerNumber, List<String> playerNicknames) throws IllegalPlayersNumberException, IOException {
        try {
            new TileType();
        }catch (Exception e){
            System.out.println("Failed to create TileType map");
        }
        ArrayList<Player> players = new ArrayList<>();
        Tile[][] slots = BoardConfig.newEmptyBoard();
        Board board = new Board(slots);
        Pocket pocket = new Pocket(new PocketBuilder().createTileListPocket(132));
        for (int i = 0; i < playerNumber; i++) {
            List<PersonalGoalCard> pgcList = ConfigsFromJson.getpgcList("src/main/resources/json/personalgoalcards.json");
            Collections.shuffle(pgcList);
            players.add(new Player(i, playerNicknames.get(i), new Bookshelf(), pgcList.remove(0)));
        }
        switch (playerNumber) {
            case 2, 3, 4 -> board = new Board(BoardConfig.fillBoard(board.getSlots(), pocket, playerNumber), playerNumber);
            default -> throw new IllegalPlayersNumberException("This players number wasn't supposed to be permitted earlier");
        }

        game = new Game(players, new HashSet<>(board.getCommonGoalCards()), board, pocket, playerNumber);
        game.getBoard().updatePickable();
        System.out.println("created game for" + playerNicknames);
    }

    /**
     * Start.
     */
    public void start() throws InterruptedException {
        game.setGameStarted(true);
        try {
        for (Player p : game.getPlayers()) {
                if (game.getPlayers().indexOf(p) != 0)
                {
                    lobby.getConnections().get(p.getUserName()).send(Parser.toJson(new StateMessage(State.WAITINGFORMYTURN),Message.class));
                }
        }
        lobby.getConnections().get(game.getPlayers().get(0).getUserName()).send(Parser.toJson(new StateMessage(State.MYTURN), Message.class));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        while (lobby.isActive());
    }

    private boolean boardToRefill() {
        Tile[][] slots = game.getBoard().getSlots();
        int rows = slots.length;
        int cols = slots[0].length;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (!slots[x][y].Empty()) {
                    if ((x > 0 && slots[x - 1][y].Empty()) || (x < rows - 1 && slots[x + 1][y].Empty()) || (y > 0 && slots[x][y - 1].Empty()) || (y < cols - 1 && slots[x][y + 1].Empty())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void calculatePoints() {
        for (Player player : game.getPlayers()) {
            int cgcPoints = 0;
            for (CommonGoalCardStrategy cgc : game.getBoard().getCommonGoalCards()) {
                cgcPoints += cgc.getPlayer(player);
            }
            player.setScore(calculateBookshelfPoints(player.getBookshelf()) + player.getPersonalGoalCard().completed(player.getBookshelf().getSlots()) + cgcPoints);
        }
    }
    public int calculateBookshelfPoints(Bookshelf bookshelf){
        int points = 0;
        Tile[][] slots = bookshelf.getSlots();
        boolean[][] visited = new boolean[Bookshelf.getRows()][Bookshelf.getCols()];
        for (int i = 0; i < Bookshelf.getRows(); i++) {
            for (int j = 0; j < Bookshelf.getCols(); j++) {
                if (slots[i][j] != null && !visited[i][j]) {
                    int consecutiveTiles = exploreAdjacentTiles(i, j, visited, slots);
                    if (consecutiveTiles >= 6) {
                        points += 8;
                    } else if (consecutiveTiles == 5) {
                        points += 5;
                    } else if (consecutiveTiles == 4) {
                        points += 3;
                    } else if (consecutiveTiles == 3) {
                        points += 2;
                    }
                }
            }
        }
        return points;
    }

    private int exploreAdjacentTiles(int row, int col, boolean[][] visited, Tile[][] slots) {
        int consecutiveTiles = 1;
        Tile currentTile = slots[row][col];
        visited[row][col] = true;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            if (isValidTileBookshelf(newRow, newCol) && !visited[newRow][newCol]) {
                Tile adjacentTile = slots[newRow][newCol];
                if (adjacentTile != null && Objects.equals(adjacentTile.getTileType(), currentTile.getTileType())) {
                    consecutiveTiles += exploreAdjacentTiles(newRow, newCol, visited, slots);
                }
            }
        }
        return consecutiveTiles;
    }



    private boolean isValidTileBookshelf(int row, int col) {
        return row >= 0 && row < Bookshelf.getRows() && col >= 0 && col < Bookshelf.getCols();
    }

    private boolean gameEnded() {
        for (Player player : game.getPlayers()) {
            if (player.getBookshelf().isFull()) {
                return true;
            }
        }
       return false;
    }

    public Game getGame(){
        return game;
    }

    @Override
    public void update(Action action) {
        if (action.canPerformAction(game)) {
            action.performAction(game);
            String nick = action.getNickName();
            Player player = game.getPlayerByNickname(nick);
            if (action instanceof ColumnSelectAction) {
                try {
                    for (CommonGoalCardStrategy cgc : game.getBoard().getCommonGoalCards()) {
                        if (cgc.conditionCheck(player.getBookshelf().getSlots())) {
                            cgc.addPlayer(player);
                        }
                    }
                }catch (NullPointerException e) {
                }
                if (gameEnded()) {
                    calculatePoints();
                    for (Player p : game.getPlayers()) {
                        try {
                            lobby.getConnections().get(p.getUserName()).send(Parser.toJson(new StateMessage(State.GAMEENDED),Message.class));
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    try {
                    lobby.getConnections().get(nick).send(Parser.toJson(new StateMessage(State.WAITINGFORMYTURN),Message.class));
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                    game.setCurrPlayerId((player.getUserId() + 1) % game.getNumPlayers());
                    game.setCurrPlayerNick((game.getPlayers().get((player.getUserId() + 1) % game.getNumPlayers()).getUserName()));
                    try {
                        lobby.getConnections().get(game.getPlayers().get((player.getUserId() + 1) % game.getNumPlayers()).getUserName()).send(Parser.toJson(new StateMessage(State.MYTURN),Message.class));
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }

                }
            } else if (action instanceof TileSelectAction) {
                game.getBoard().updatePickable();
                if (boardToRefill()) {
                    game.getBoard().fillBoard(BoardConfig.fillBoard(game.getBoard().getSlots(), game.getPocket(), game.getNumPlayers()));
                }
            }
        }
        else {
            System.out.println("Could not perform action");
        }
    }
}

