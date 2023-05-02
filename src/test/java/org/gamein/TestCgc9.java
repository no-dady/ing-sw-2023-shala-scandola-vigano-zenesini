package org.gamein;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.gamein.model.CommonGoalCardStrategy;
import org.gamein.model.Tile;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Unit test for Cgc 9 algorithm.
 */
public class TestCgc9
        extends TestCase
{
        public List<Tile[][]> shelfa;

        public List<Tile[][]> shelfb;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

    public TestCgc9(String testName )
    {
        super( testName );

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/json/shelves/test_cgc9_shelf.json"));

            Gson gson = new GsonBuilder().serializeNulls().create();
            shelfa = gson.fromJson(reader, new TypeToken<List<Tile[][]>>(){}.getType());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/json/shelves/prova.json"));

            Gson gson = new GsonBuilder().serializeNulls().create();
            shelfb = gson.fromJson(reader, new TypeToken<List<Tile[][]>>(){}.getType());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestCgc9.class );
    }

    /**
     * Rigourous Test
     */
    public void testApp()
    {
        CommonGoalCardStrategy test = new CommonGoalCardStrategy();
        CommonGoalCardCondition Test = CommonGoalCardStrategy.getCgcMap().get("SHIFTED_CHECKERBOARD_EQ");

        Tile[][] shelf1 = shelfa.get(0);
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf1[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }

        assertTrue("Algorithm doesn't work!", Test.conditionCheck(shelf1));

        Tile[][] shelf2 = shelfb.get(0);
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf2[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
        assertFalse("Algorithm doesn't work!", Test.conditionCheck(shelf2));
    }
}
