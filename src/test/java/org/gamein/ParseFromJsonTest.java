package org.gamein;

import com.google.gson.reflect.TypeToken;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import server.controller.BoardConfig;
import server.model.PersonalGoalCard;
import server.model.Tile;
import server.model.TileTypeRecord;
import util.Parser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class ParseFromJsonTest
    extends TestCase
{
    public List<Tile[][]> prova;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ParseFromJsonTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ParseFromJsonTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        System.out.println("Starting parser test");
        try {

            //TileTypeTest
            Map<String, TileTypeRecord> testTileType = new HashMap<>();
            testTileType = Parser.parseFromJson("src/main/resources/json/tiletypes_config.json", new TypeToken<Map<String, TileTypeRecord>>(){});
            testTileType.keySet().forEach(System.out::println);
            testTileType.values().forEach(x -> System.out.println(x.toString()));

            //Board Test
            BoardConfig testBoard = Parser.parseFromJson("src/main/resources/json/board_config.json", BoardConfig.class);
            System.out.println(testBoard.toString());

            //PersonalGoalCards
            List<PersonalGoalCard> personalGoalCardList = Parser.parseFromJson("src/main/resources/json/personalgoalcards.json", new TypeToken<List<PersonalGoalCard>>() {});
            personalGoalCardList.forEach(x->System.out.println(x.toString()));



        } catch (Exception e) {
            fail(e.getMessage());
        }
        System.out.println("\nTest Passsato!");
    }
}
