package server.controller;

import server.exceptions.IllegalPlayersNumberException;
import server.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController {

    protected static ArrayList<Player> players = null;
    protected static Game game;
    protected static Board board;
    protected static Pocket pocket;
    protected static List<PersonalGoalCard> pgcList;
    protected static CommonGoalCard[] cgcEnum;
    protected static Tile[][] slots;

    public GameController() {
        players = new ArrayList<>();


        System.exit(0);
        // TODO: Player needed object generation
        players.add(new Player(0, "Test1", new Bookshelf(), pgcList.get(1)));

        // TODO: Make call to method for generating random CommonGoalCard
        //cgcEnum = new CommonGoalCard[2];
        //cgcEnum[0] = CommonGoalCard.TWO_4EQ_TILES_SQUARE;
        //cgcEnum[1] = CommonGoalCard.TWO_DISTINCT_COLUMNS;

        // TODO: Generate slots to pass to the board constructor
        slots = BoardInitializer.newEmptyBoard();
        //board = new Board(cgcEnum, slots);

        //game = new Game(players, board, pocket);
    }

    void createLobby(int playerNumber) throws IllegalPlayersNumberException {
        PersonalGoalCard personalGoalCard;
        PocketBuilder builder = new PocketBuilder();
        BoardFiller builderBoard = new BoardFiller();
        pocket = new Pocket(builder.createTileListPocket(132));
        for (int i = 0; i < playerNumber; i++) {
            //personalGoalCard.select();
            Collections.shuffle(pgcList);
            personalGoalCard = pgcList.remove(0);
            players.add(new Player(i, "player" + i, new Bookshelf(), personalGoalCard));
        }
        switch (playerNumber) {
            //case 2, 3, 4 -> board = new Board(cgcEnum, builderBoard.fillBoard(board.getSlots(), pocket, playerNumber));
            default -> throw new IllegalPlayersNumberException("wait, you are doing something wrong");
        }

        //gameState = new GameState(players, board, pocket);
    }
    void start(List<String> usernames) {

    }
}

