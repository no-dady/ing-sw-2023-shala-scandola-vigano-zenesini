package org.gamein;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.gamein.model.CommonGoalCard;
import org.gamein.model.PersonalGoalCard;
import org.gamein.model.Tile;
import org.gamein.model.TileType;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    public List<Tile[][]> prova;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/json/shelves/prova.json"));

            Gson gson = new GsonBuilder().serializeNulls().create();
            prova = gson.fromJson(reader, new TypeToken<List<Tile[][]>>(){}.getType());
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
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        Tile[][] shelf = prova.get(0);
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
        CommonGoalCard myTest = CommonGoalCard.TWO_DISTINCT_COLUMNS;

        var x = myTest.getCondition().conditionCheck(shelf);
        assertFalse("Found two full distinct columns when not expected!", x);
        myTest = CommonGoalCard.FOUR_VSTRIPS_4EQUAL;
        x = myTest.getCondition().conditionCheck(shelf);
        assertFalse("Found four strips of 4 equal vertical tiles when not expected!", x);
        myTest = CommonGoalCard.SIX_VSTRIPS_2EQUAL;
        x = myTest.getCondition().conditionCheck(shelf);
        assertFalse("Found six pairs of distinct vertical tiles when not expected!",x);
        myTest = CommonGoalCard.THREE_COL_MIN3EQUAL;
        x = myTest.getCondition().conditionCheck(shelf);
        assertFalse("Found 3 columns with at least 3 tiles of the same type when not expected",x);
        myTest = CommonGoalCard.TWO_DISTINCT_ROWS;
        x = myTest.getCondition().conditionCheck(shelf);
        assertFalse("Found 2 rows full of distinct tiles when not expected!",x);
        myTest = CommonGoalCard.FOUR_ROWS_MIN2EQUAL;
        x = myTest.getCondition().conditionCheck(shelf);
        assertTrue("Didnt find 4 rows with at least 2 tiles of the same type when expected!",x);
        System.out.println("\nTest Passsato!");
    }
}
