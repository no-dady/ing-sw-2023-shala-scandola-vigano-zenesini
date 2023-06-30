package it.polimi.server.controller;

import it.polimi.client.network.ClientInterface;
import it.polimi.client.network.State;
import it.polimi.observer.Observer;
import it.polimi.server.controller.actions.ColumnSelectAction;
import it.polimi.server.controller.actions.TileSelectAction;
import it.polimi.server.exceptions.IllegalPlayersNumberException;
import it.polimi.server.model.*;
import it.polimi.setup.ConfigsFromJson;
import it.polimi.util.Messages.BoardMessage;
import it.polimi.util.Messages.InitialMessage;
import it.polimi.util.Messages.Message;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

import it.polimi.server.controller.actions.Action;
import it.polimi.util.Messages.StateMessage;
import it.polimi.util.Parser;

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
        ArrayList<Player> players = new ArrayList<>();
        Tile[][] slots = BoardConfig.newEmptyBoard();
        Board board = new Board(slots);
        Pocket pocket = new Pocket(new PocketBuilder().createTileListPocket(132));
        for (int i = 0; i < playerNumber; i++) {
            List<PersonalGoalCard> pgcList = ConfigsFromJson.getpgcList(Parser.getResourcePath("json/personalgoalcards.json"));
            Collections.shuffle(pgcList);
            players.add(new Player(i, playerNicknames.get(i), new Bookshelf(), pgcList.remove(0)));
        }
        switch (playerNumber) {
            case 2, 3, 4 -> board = new Board(BoardConfig.fillBoard(board.getSlots(), pocket, playerNumber), playerNumber);
            default -> throw new IllegalPlayersNumberException("This players number wasn't supposed to be permitted earlier");
        }

        game = new Game(players, new HashSet<>(board.getCommonGoalCards()), board, pocket, playerNumber);
        game.getBoard().updatePickable();
        //System.out.println("created game for" + playerNicknames);
    }

    /**
     * Start.
     */
    public void start() {
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
                    if (!(x > 0 && slots[x - 1][y].Empty()) || !(x < rows - 1 && slots[x + 1][y].Empty()) || !(y > 0 && slots[x][y - 1].Empty()) || !(y < cols - 1 && slots[x][y + 1].Empty())) {
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
            //System.out.println(cgcPoints + ":cgc " + calculateBookshelfPoints(player.getBookshelf()) + ":adjacent " + player.getPersonalGoalCard().completed(player.getBookshelf().getSlots()) + ":pgc ");
            player.setScore(calculateBookshelfPoints(player.getBookshelf()) + player.getPersonalGoalCard().getPoints(player) + cgcPoints);
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
                if (adjacentTile != null && adjacentTile.equals(currentTile)) {
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
        return boardToRefill() && game.getPocket().getLeft() == 0;
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
                        if (cgc.conditionCheck(player.getBookshelf().getSlots()) && !cgc.isCompletedByPlayer(player)) {
                            cgc.addPlayer(player);
                        }
                    }
                } catch (NullPointerException ignored) {
                }
                if (gameEnded()) {
                    calculatePoints();
                    for (Player p : game.getPlayers()) {
                        try {
                            InitialMessage gameMessage = new InitialMessage(game);
                            lobby.getConnections().get(p.getUserName()).send(Parser.toJson(gameMessage, Message.class));
                            BoardMessage boardMessage = new BoardMessage(game.getBoard());
                            lobby.getConnections().get(p.getUserName()).send(Parser.toJson(boardMessage, Message.class));
                            lobby.getConnections().get(p.getUserName()).send(Parser.toJson(new StateMessage(State.GAMEENDED), Message.class));
                            lobby.setActive(false);
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    game.setCurrPlayerId((player.getUserId() + 1) % game.getNumPlayers());
                    game.setCurrPlayerNick((game.getPlayers().get((player.getUserId() + 1) % game.getNumPlayers()).getUserName()));
                    try {
                        lobby.getConnections().get(game.getPlayers().get((player.getUserId() + 1) % game.getNumPlayers()).getUserName()).send(Parser.toJson(new StateMessage(State.MYTURN), Message.class));
                        for (var entity : lobby.getConnections().entrySet()) {
                            if (!entity.getKey().equals(game.getCurrPlayerNick())) {
                                entity.getValue().send(Parser.toJson(new StateMessage(State.WAITINGFORMYTURN), Message.class));
                            }
                        }
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }

                }
                game.getBoard().updatePickable();
                if (boardToRefill()) {
                    game.getBoard().fillBoard(BoardConfig.fillBoard(game.getBoard().getSlots(), game.getPocket(), game.getNumPlayers()));
                }
                game.getBoard().updatePickable();
            } else if (action instanceof TileSelectAction) {

            }
            try {
                for (ClientInterface clientInterface : lobby.getConnections().values()) {
                    InitialMessage gameMessage = new InitialMessage(game);
                    clientInterface.send(Parser.toJson(gameMessage, Message.class));
                    BoardMessage boardMessage = new BoardMessage(game.getBoard());
                    clientInterface.send(Parser.toJson(boardMessage, Message.class));
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("Could not perform action");
        }
    }
}

