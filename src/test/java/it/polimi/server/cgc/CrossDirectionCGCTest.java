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


public class CrossDirectionCGCTest extends TestCase {
    public List<Tile[][]> shelves;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CrossDirectionCGCTest(String testName )
    {
        super( testName );

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/json/shelves/shelf_cross_direction.json"));
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
        return new TestSuite( CrossDirectionCGCTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testCGC10() throws IOException {
        Tile[][] shelf = shelves.get(0);
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].getTileType() + "\t|\t");
                //System.out.print(shelf[i][j].getTileId() + "\t|\t");
            }
            System.out.println();
        }
        CrossDirection cgc10 = new CrossDirection(3, "Cross");
        cgc10.Print();

        assertTrue("CGC10 Not Passed", cgc10.conditionCheck(shelf));
    }
}
