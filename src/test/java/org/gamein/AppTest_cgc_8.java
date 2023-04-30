package org.gamein;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.gamein.cgc.CommonGoalCardCondition;
import org.gamein.model.CommonGoalCard;
import org.gamein.model.Tile;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AppTest_cgc_8 extends TestCase {

        public List<Tile[][]> shelves;
        /**
         * Create the test case
         *
         * @param testName name of the test case
         */
    public AppTest_cgc_8( String testName )
        {
            super( testName );

            try {
                Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/json/shelves/test_cgc_8_false.json"));
                Gson gson = new GsonBuilder().serializeNulls().create();
                shelves = gson.fromJson(reader, new TypeToken<List<Tile[][]>>() {}.getType());
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
            return new TestSuite( AppTest_cgc_8.class );
        }

        /**
         * Rigourous Test :-)
         */
        public void testApp_cgc_8()
        {
            CommonGoalCard test = new CommonGoalCard();
            Tile[][] shelf = shelves.get(0);
            for(int i = 5; i >= 0; i--) {
                for(int j = 0; j < 5; j++) {
                    System.out.print(shelf[i][j].getTileType() + "\t|\t");
                }
                System.out.println();
            }
            CommonGoalCardCondition myTest = CommonGoalCard.getCgcMap().get("SHELF_CORNERS_EQ");
            assertTrue("All corners look the same!", myTest.conditionCheck(shelf));
        }
}

