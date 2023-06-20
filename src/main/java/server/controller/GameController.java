package server.controller;

import observer.Observable;
import observer.Observer;
import server.exceptions.IllegalPlayersNumberException;
import server.model.*;
import setup.ConfigsFromJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Game controller.
 */
public class GameController implements Observable {

    /**
     * The Players.
     */
    protected static ArrayList<Player> players = null;
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
    protected static Tile[][] slots = BoardConfig.newEmptyBoard();;
    /**
     * The constant board.
     */
    protected static Board board = new Board(slots);

    /**
     * Instantiates a new Game controller.
     */
    public GameController() {
        players = new ArrayList<>();
        try {
            pgcList = ConfigsFromJson.getpgcList("src/main/resources/json/personalgoalcards.json");
        }catch (Exception e){
            System.out.println("exception");
        }
        slots = BoardConfig.newEmptyBoard();
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
            case 2, 3, 4 -> board = new Board(BoardConfig.fillBoard(board.getSlots(), pocket, playerNumber));
            default -> throw new IllegalPlayersNumberException("wait, you are doing something wrong");
        }
        game = new Game();
    }

    /**
     * Start.
     *
     * @param usernames the usernames
     */
    void start(List<String> usernames) {

    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void notify(Object message) {

    }
}

