package org.gamein.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.gamein.MainGame;
import org.gamein.cgc.CommonGoalCardCondition;
import org.gamein.model.*;

import java.io.Console;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {

    protected static ArrayList<Player> players = null;
    protected static GameState gameState;
    protected static Board board;
    protected static Pocket pocket;
    protected static List<PersonalGoalCard> pgcList;
    protected static CommonGoalCard[] cgcEnum;
    protected static Tile[][] slots;

    public GameController() {
        players = new ArrayList<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/java/org/gamein/personalgoalcards.json"));
            pgcList = new Gson().fromJson(reader, new TypeToken<List<PersonalGoalCard>>() {}.getType());
            /* DEBUG
            for(var x : pgcList) {
                System.out.println("\nOggetto: "+pgcList.indexOf(x));
                System.out.println(x.blue().x + ", " + x.blue().y);
                System.out.println(x.orange().x + ", " + x.orange().y);
                System.out.println(x.pink().x + ", " + x.pink().y);
                System.out.println(x.green().x + ", " + x.green().y);
                System.out.println(x.cyan().x + ", " + x.cyan().y);
                System.out.println(x.white().x + ", " + x.white().y);
            }
            */
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
        // TODO: Player needed object generation
        players.add(new Player(0, "Test1", new Bookshelf(), pgcList.get(1)));

        // TODO: Make call to method for generating random CommonGoalCard
        cgcEnum = new CommonGoalCard[2];
        cgcEnum[0] = CommonGoalCard.CGC1;
        cgcEnum[1] = CommonGoalCard.CGC2;

        // TODO: Generate slots to pass to the board constructor
        slots = new Tile[6][5];
        board = new Board(cgcEnum, slots);

        gameState = new GameState(players, board, pocket);
    }

    void createLobby(int playerNumber) throws IllegalPlayersNumberException {
        PersonalGoalCard personalGoalCard;
        PocketBuilder builder = new PocketBuilder();
        pocket = new Pocket(builder.createTileListPocket(132));
        for (int i = 0; i < playerNumber; i++) {
            //personalGoalCard.select();
            //players.add(new Player(i, "player" + i, new Bookshelf(), personalGoalCard));
        }

        switch (playerNumber) {
            case 2:
                //board = BoardBuilder2();
            case 3:
                //board = BoardBuilder3();
            case 4:
                //board = BoardBuilder4();
            default:
                throw new IllegalPlayersNumberException("wait, you are doing something wrong");
        }
        //gameState = new GameState(players, board, pocket);
    }
    void start(List<String> usernames) {

    }
}

