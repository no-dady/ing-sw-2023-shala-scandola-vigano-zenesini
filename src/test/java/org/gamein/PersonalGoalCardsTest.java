package org.gamein;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import server.model.Bookshelf;
import server.model.PersonalGoalCard;
import server.model.Tile;
import server.model.TileType;
import setup.ConfigsFromJson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

public class PersonalGoalCardsTest extends TestCase {
    public List<Tile[][]> shelves;
    public List<PersonalGoalCard> pgcList;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PersonalGoalCardsTest(String testName )
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
        return new TestSuite( PersonalGoalCardsTest.class );
    }

    /**
     * Rigourous Test :-)
     *
     */

    public void Print(PersonalGoalCard pgc) throws IOException {
        String art = ConfigsFromJson.getArt("src/main/resources/json/PGCArt.json");
                int x = Bookshelf.getRows()-1+3, y = 0;
                String preset = "\033[";
                String reset = "0m";
                for (int i = 0; i< art.length(); i++) {
                    if (art.charAt(i) == 'x') {

                        int found = 0;
                        for (int j = 0; j<TileType.getTileMap().values().size()-1; j++) {
                            if (pgc.getCoordinates(TileType.getTileMap().keySet().stream().toList().get(j)).y()==y & pgc.getCoordinates(TileType.getTileMap().keySet().stream().toList().get(j)).x()==x){
                                String firstHalf = art.substring(0,i);
                                String secondHalf = art.substring(i+1, art.length());
                                art = firstHalf + preset + TileType.getTileMap().get(TileType.getTileMap().keySet().stream().toList().get(j)).color + "  " + preset + reset + secondHalf;
                                found = 1;
                            }

                        }
                        if (found == 0){
                            String firstHalf = art.substring(0,i);
                            String secondHalf = art.substring(i+1, art.length());
                            art = firstHalf + "  " + secondHalf;
                        }
                        y++;
                    } else if (art.charAt(i) == '\n') {
                        x--;
                        y = 0;

                    }
                }
        System.out.println(art);
        }


    public void testPersonalGoalCard1() throws IOException {
        Tile[][] shelf = shelves.get(0);
        System.out.println();
        PersonalGoalCard card = pgcList.get(0);
        Print(card);
        assertTrue("PGC1 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard2()
    {
        Tile[][] shelf = shelves.get(1);
        System.out.println();
        PersonalGoalCard card = pgcList.get(1);

        assertTrue("PGC2 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard3()
    {
        Tile[][] shelf = shelves.get(2);
        System.out.println();
        PersonalGoalCard card = pgcList.get(2);

        assertTrue("PGC3 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard4()
    {
        Tile[][] shelf = shelves.get(3);
        System.out.println();
        PersonalGoalCard card = pgcList.get(3);

        assertTrue("PGC4 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard5()
    {
        Tile[][] shelf = shelves.get(4);
        System.out.println();
        PersonalGoalCard card = pgcList.get(4);

        assertTrue("PGC5 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard6()
    {
        Tile[][] shelf = shelves.get(5);
        System.out.println();
        PersonalGoalCard card = pgcList.get(5);

        assertTrue("PGC6 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard7()
    {
        Tile[][] shelf = shelves.get(6);
        System.out.println();
        PersonalGoalCard card = pgcList.get(6);

        assertTrue("PGC7 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard8()
    {
        Tile[][] shelf = shelves.get(7);
        System.out.println();
        PersonalGoalCard card = pgcList.get(7);

        assertTrue("PGC8 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard9()
    {
        Tile[][] shelf = shelves.get(8);
        System.out.println();
        PersonalGoalCard card = pgcList.get(8);

        assertTrue("PGC9 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard10()
    {
        Tile[][] shelf = shelves.get(9);
        System.out.println();
        PersonalGoalCard card = pgcList.get(9);

        assertTrue("PGC10 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard11()
    {
        Tile[][] shelf = shelves.get(10);
        System.out.println();
        PersonalGoalCard card = pgcList.get(10);

        assertTrue("PGC11 Not Passed", card.completed(shelf));
    }

    public void testPersonalGoalCard12()
    {
        Tile[][] shelf = shelves.get(11);
        System.out.println();
        PersonalGoalCard card = pgcList.get(11);

        assertTrue("PGC12 Not Passed", card.completed(shelf));
    }

}