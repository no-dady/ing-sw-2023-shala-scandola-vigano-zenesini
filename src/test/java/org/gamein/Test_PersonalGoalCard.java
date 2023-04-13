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

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test_PersonalGoalCard extends TestCase {
    public List<Tile[][]> shelves;
    public List<PersonalGoalCard> pgcList;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Test_PersonalGoalCard(String testName )
    {
        super( testName );

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/json/shelves/shelf_PersonalGoalCard.json"));
            Gson gson = new GsonBuilder().serializeNulls().create();
            shelves = gson.fromJson(reader, new TypeToken<List<Tile[][]>>(){}.getType());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/json/personalgoalcards.json"));
            pgcList = new Gson().fromJson(reader, new TypeToken<List<PersonalGoalCard>>() {}.getType());
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
        return new TestSuite( Test_PersonalGoalCard.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testPersonalGoalCard1()
    {
        Tile[][] shelf = shelves.get(0);
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
        PersonalGoalCard card = pgcList.get(0);

        assertTrue("PGC1 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard2()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(1);

        assertTrue("PGC2 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard3()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(2);

        assertTrue("PGC3 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard4()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(3);

        assertTrue("PGC4 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard5()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(4);

        assertTrue("PGC5 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard6()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(5);

        assertTrue("PGC6 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard7()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(6);

        assertTrue("PGC7 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard8()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(7);

        assertTrue("PGC8 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard9()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(8);

        assertTrue("PGC9 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard10()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(9);

        assertTrue("PGC10 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard11()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(10);

        assertTrue("PGC11 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard12()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(11);

        assertTrue("PGC12 Not Passed", card.conditionCheck(shelf));
    }

    public void testPersonalGoalCard13()
    {
        Tile[][] shelf = shelves.get(0);
        PersonalGoalCard card = pgcList.get(12);

        assertTrue("PGC13 Not Passed", card.conditionCheck(shelf));
    }
}