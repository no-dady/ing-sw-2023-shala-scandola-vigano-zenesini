package server.controller;

import observer.Observable;
import observer.Observer;
import server.exceptions.IllegalPlayersNumberException;
import server.model.*;
import setup.ConfigsFromJson;
import util.Messages.Message;

import java.util.*;

import server.controller.actions.Action;

/**
 * The type Game controller.
 */
public class GameController implements Observer<Action>, Observable<Game> {

    /**
     * The Players.
     */
    protected static ArrayList<Player> players = null;
    protected static int playersNumber;
    /**
     * The constant game.
     */
    protected static Game game;
    /**
     * The constant pocket.
     */
    protected static Pocket pocket;
    /**
     * The Pgc list.
     */
    protected static List<PersonalGoalCard> pgcList;
    /**
     * The Cgc List.
     */
    protected static List<CommonGoalCardStrategy> cgcList;
    /**
     * The Slots.
     */
    protected static Tile[][] slots;
    /**
     * The constant board.
     */
    protected static Board board;

    /**
     * Instantiates a new Game controller.
     */
    public GameController(
    ) {
        System.out.println("Creating Playerlist");
        players = new ArrayList<>();
        System.out.println("Creating TileType");
        try {
            new TileType();
            System.out.println("Parsing pgcList");
            pgcList = ConfigsFromJson.getpgcList("src/main/resources/json/personalgoalcards.json");
        }catch (Exception e){
            System.out.println("exception");
        }
        System.out.println("Getting empty Board");
        slots = BoardConfig.newEmptyBoard();
        System.out.println("Creating slots Board");
        board = new Board(slots);
    }

    /**
     * Create lobby.
     *
     * @param playerNumber the player number
     * @throws IllegalPlayersNumberException the illegal players number exception
     */
    public void createLobby(int playerNumber, List<String> playerNicknames) throws IllegalPlayersNumberException {
        pocket = new Pocket(new PocketBuilder().createTileListPocket(132));
        for (int i = 0; i < playerNumber; i++) {
            Collections.shuffle(pgcList);
            players.add(new Player(i, playerNicknames.get(i), new Bookshelf(), pgcList.remove(0)));
        }
        switch (playerNumber) {
            case 2, 3, 4 -> board = new Board(BoardConfig.fillBoard(board.getSlots(), pocket, playerNumber), playerNumber);
            default -> throw new IllegalPlayersNumberException("wait, you are doing something wrong");
        }
        board.updatePickable();
        game = new Game(players, cgcList, board, pocket, playerNumber);
        players = (ArrayList<Player>) game.getPlayers();
        playersNumber = playerNumber;
        System.out.println("created game for" + playerNicknames);
    }

    /**
     * Start.
     */
    public void start() throws InterruptedException {
        int randFirst = new Random().nextInt(playersNumber);
        game.setCurrPlayerId(players.get(randFirst).getUserId());
        game.setCurrPlayerNick(players.get(randFirst).getUserName());
        do{
            if (boardToRefill()){ board.fillBoard(BoardConfig.fillBoard(board.getSlots(), pocket, playersNumber));}
            //receive actions in order to know the turn is completed, do we set a boolean? do we check the last move?
            randFirst = (randFirst + 1)%playersNumber;
            game.setCurrPlayerId(players.get(randFirst).getUserId());
            game.setCurrPlayerNick(players.get(randFirst).getUserName());
            wait(); // maybe this is how the game waits for the player to make a move, can this be done in another way?
            for (CommonGoalCardStrategy cgc : game.getBoard().getCommonGoalCards()
            ) {if(cgc.conditionCheck(game.getPlayerByNickname(game.getCurrPlayerNick()).getBookshelf().getSlots())){
            cgc.addPlayer(game.getPlayerByNickname(game.getCurrPlayerNick()));
            }
            }
        }while (!gameEnded());
        calculatePoints();

    }

    private boolean boardToRefill() {
        int rows = slots.length;
        int cols = slots[0].length;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (!slots[x][y].Empty()) {
                    if ((x > 0 && slots[x - 1][y].Empty()) ||
                            (x < rows - 1 && slots[x + 1][y].Empty()) ||
                            (y > 0 && slots[x][y - 1].Empty()) ||
                            (y < cols - 1 && slots[x][y + 1].Empty())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void calculatePoints() {
        for (Player player : players) {
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
                    int consecutiveTiles = exploreAdjacentTiles(i, j, visited);
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

    private int exploreAdjacentTiles(int row, int col, boolean[][] visited) {
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
                    consecutiveTiles += exploreAdjacentTiles(newRow, newCol, visited);
                }
            }
        }
        return consecutiveTiles;
    }



    private boolean isValidTileBookshelf(int row, int col) {
        return row >= 0 && row < Bookshelf.getRows() && col >= 0 && col < Bookshelf.getCols();
    }

    private boolean gameEnded() {
        for (Player player : players) {
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
        // TODO Auto-generated method stub
        if(action.canPerformAction(game))
            action.performAction(game);
        else
            System.out.println("Could not perform action");
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void notify(Game message) {

    }
}

