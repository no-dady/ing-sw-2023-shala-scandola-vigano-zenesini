package server.controller;

import observer.Observable;
import observer.Observer;
import server.exceptions.IllegalPlayersNumberException;
import server.model.*;
import setup.ConfigsFromJson;
import util.Messages.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import server.controller.actions.Action;

/**
 * The type Game controller.
 */
public class GameController implements Observer<Action> {

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
    protected static Tile[][] slots;
    /**
     * The constant board.
     */
    protected static Board board;

    /**
     * Instantiates a new Game controller.
     */
    public GameController() {
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
            case 2, 3, 4 -> board = new Board(BoardConfig.fillBoard(board.getSlots(), pocket, playerNumber));
            default -> throw new IllegalPlayersNumberException("wait, you are doing something wrong");
        }
        board.updatePickable();
        game = new Game(players, cgcList, board, pocket, playerNumber);
        System.out.println("created game for" + playerNicknames);
        return;
    }

    /**
     * Start.
     *
     * @param usernames the usernames
     */
    void start(List<String> usernames) {

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
}

