package it.polimi.server.cgc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import it.polimi.server.model.Tile;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Unit test for Cgc 9 algorithm.
 */
public class ShiftedCheckerboardCGCTest
        extends TestCase
{
        public List<Tile[][]> shelfa;

        public List<Tile[][]> shelfb;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

    public ShiftedCheckerboardCGCTest(String testName )
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
        return new TestSuite( ShiftedCheckerboardCGCTest.class );
    }

    /**
     * Rigourous Test
     */

    public void testApp() throws IOException {
        ShiftedCheckerboard cgc9 = new ShiftedCheckerboard("ShiftedCheckerboard");

        Tile[][] shelf1 = shelfa.get(0);
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf1[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }

        assertTrue("Algorithm doesn't work!", cgc9.conditionCheck(shelf1));

        Tile[][] shelf2 = shelfb.get(0);
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf2[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
        assertFalse("Algorithm doesn't work!", cgc9.conditionCheck(shelf2));
    }
}
