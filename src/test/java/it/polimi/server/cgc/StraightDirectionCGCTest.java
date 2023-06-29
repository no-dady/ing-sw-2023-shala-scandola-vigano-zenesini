package it.polimi.server.cgc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.Tile;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class StraightDirectionCGCTest
    extends TestCase
{
    public List<Tile[][]> prova;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StraightDirectionCGCTest(String testName )
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
        return new TestSuite( StraightDirectionCGCTest.class );
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

//        CommonGoalCard.getCgcMap().values().forEach((x) -> x.conditionCheck(shelf));

        StraightDirection myTest = new StraightDirection(2, Bookshelf.getCols(),false,true,"");
        var x = myTest.conditionCheck(shelf);
        assertFalse("Found two full distinct columns when not expected!", x);
        myTest = new StraightDirection(4,4,true,true,"");
        x = myTest.conditionCheck(shelf);
        assertFalse("Found four strips of 4 equal vertical tiles when not expected!", x);
        myTest = new StraightDirection(6,2,true,true,"");
        x = myTest.conditionCheck(shelf);
        assertFalse("Found six pairs of distinct vertical tiles when not expected!",x);
        // Check Empy bookshelf
        System.out.println("\nPrinting empty shelf for test\n");
        Tile[][] empty = new Bookshelf().getSlots();
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print("|\t" + empty[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
        x = myTest.conditionCheck(empty);
        assertFalse("Found six pairs of distinct vertical tiles when not expected!",x);
        myTest = new StraightDirection(2,Bookshelf.getRows(),false,false,"");
        x = myTest.conditionCheck(shelf);
        assertFalse("Found 2 rows full of distinct tiles when not expected!",x);
        System.out.println("\nTest Passsato!");
    }
}
