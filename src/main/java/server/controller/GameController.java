package server.controller;

import server.exceptions.IllegalPlayersNumberException;
import server.model.*;
import setup.ConfigsFromJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Game controller.
 */
public class GameController {

    /**
     * The Players.
     */
    protected static ArrayList<Player> players = null;
    /**
     * The constant game.
     */
    protected static Game game;
    /**
     * The constant board.
     */
    protected static Board board;
    /**
     * The constant pocket.
     */
    protected static Pocket pocket;
    /**
     * The Pgc list.
     */
    protected static List<PersonalGoalCard> pgcList;
    /**
     * The Cgc enum.
     */
    protected static CommonGoalCardStrategy[] cgcEnum;
    /**
     * The Slots.
     */
    protected static Tile[][] slots;

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


        System.exit(0);
        // TODO: Player needed object generation
        players.add(new Player(0, "Test1", new Bookshelf(), pgcList.get(1)));

        // TODO: Make call to method for generating random CommonGoalCard
        //cgcEnum = new CommonGoalCard[2];
        //cgcEnum[0] = CommonGoalCard.TWO_4EQ_TILES_SQUARE;
        //cgcEnum[1] = CommonGoalCard.TWO_DISTINCT_COLUMNS;

        // TODO: Generate slots to pass to the board constructor
        slots = BoardConfig.newEmptyBoard();
        //board = new Board(cgcEnum, slots);

        //game = new Game(players, board, pocket);
    }

    /**
     * Create lobby.
     *
     * @param playerNumber the player number
     * @throws IllegalPlayersNumberException the illegal players number exception
     */
    void createLobby(int playerNumber) throws IllegalPlayersNumberException {
        PersonalGoalCard personalGoalCard;
        PocketBuilder builder = new PocketBuilder();
        //BoardFiller builderBoard = new BoardFiller();
        pocket = new Pocket(builder.createTileListPocket(132));
        for (int i = 0; i < playerNumber; i++) {
            //personalGoalCard.select();
            Collections.shuffle(pgcList);
            personalGoalCard = pgcList.remove(0);
            players.add(new Player(i, "player" + i, new Bookshelf(), personalGoalCard));
        }
        switch (playerNumber) {
            case 2, 3, 4 -> board = new Board(BoardConfig.fillBoard(board.getSlots(), pocket, playerNumber));
            default -> throw new IllegalPlayersNumberException("wait, you are doing something wrong");
        }

        //gameState = new GameState(players, board, pocket);
    }

    /**
     * Start.
     *
     * @param usernames the usernames
     */
    void start(List<String> usernames) {

    }
}

