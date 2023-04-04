package org.gamein;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.gamein.model.CommonGoalCard;
import org.gamein.model.Tile;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test_CGC_Diagonal_Direction extends TestCase {
    public List<Tile[][]> shelves;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Test_CGC_Diagonal_Direction(String testName )
    {
        super( testName );

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/json/shelves/shelf_diagonal_direction.json"));
            Gson gson = new GsonBuilder().serializeNulls().create();
            shelves = gson.fromJson(reader, new TypeToken<List<Tile[][]>>(){}.getType());
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
        return new TestSuite( Test_CGC_Diagonal_Direction.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testCGC11()
    {
        Tile[][] shelf = shelves.get(0);
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
        CommonGoalCard cgc11 = CommonGoalCard.CGC11;

        assertTrue("CGC11 Not Passed", cgc11.getCondition().conditionCheck(shelf));
    }

    public void testCGC12()
    {
        Tile[][] shelf = shelves.get(0);

        CommonGoalCard cgc12 = CommonGoalCard.CGC12;

        assertTrue("CGC12 Not Passed", cgc12.getCondition().conditionCheck(shelf));
    }
}
