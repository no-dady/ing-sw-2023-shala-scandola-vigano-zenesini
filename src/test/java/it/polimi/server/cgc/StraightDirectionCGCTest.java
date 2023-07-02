package it.polimi.server.cgc;

import com.google.gson.reflect.TypeToken;
import it.polimi.server.model.CommonGoalCardStrategy;
import it.polimi.util.Parser;
import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit test for simple App.
 *
 * @author daniel
 * @version $Id: $Id
 * @since 1.0
 */
public class StraightDirectionCGCTest
{
    private CommonGoalCardStrategy toTest;

    /**
     * <p>printShelf.</p>
     *
     * @param shelf an array of {@link it.polimi.server.model.Tile} objects
     */
    public static void printShelf(Tile[][] shelf) {
        System.out.println();
        for(int i = 5; i >= 0; i--) {
            System.out.print("|\t");
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
    }

    /**
     * <p>getTilesForTest.</p>
     *
     * @param jsonPath a {@link java.lang.String} object
     * @return a {@link java.util.List} object
     * @throws java.io.IOException if any.
     */
    public static List<Tile[][]> getTilesForTest(String jsonPath) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get(jsonPath));
        String json = reader.lines().collect(Collectors.joining());
        return Parser.fromJson(json, new TypeToken<List<Tile[][]>>(){}.getType());
    }

    /**
     * <p>testApp.</p>
     *
     * @throws java.io.IOException if any.
     */
    @org.junit.jupiter.api.Test
    public void testApp() throws IOException {
        String path = "src/test/resources/json/shelves/StraightDirectionTest";

        List<Tile[][]> shelf = StraightDirectionCGCTest.getTilesForTest(path + "/testOne.json");
        toTest = new StraightDirection(2, Bookshelf.getCols(), false, true, "");
        printShelf(shelf.get(0));
        assertFalse(toTest.conditionCheck(shelf.get(0)), "Bookshelf is Empty should be false");
        printShelf(shelf.get(1));
        assertFalse(toTest.conditionCheck(shelf.get(1)), "Found two full distinct columns when not expected!");
        printShelf(shelf.get(2));
        assertTrue(toTest.conditionCheck(shelf.get(2)), "Did not find two full distinct columns when expected!");
        System.out.println("\nTest One Passsato!");

        shelf = StraightDirectionCGCTest.getTilesForTest(path + "/testTwo.json");
        toTest = new StraightDirection(4,4,true,true,"");
        printShelf(shelf.get(0));
        assertFalse(toTest.conditionCheck(shelf.get(0)), "Found when shelf is empty");
        printShelf(shelf.get(1));
        assertTrue(toTest.conditionCheck(shelf.get(1)), "Didn't find cgc when expected");
        printShelf(shelf.get(2));
        assertFalse(toTest.conditionCheck(shelf.get(2)), "Found When not expected");
        System.out.println("\nTest Two Passsato!");

        shelf = StraightDirectionCGCTest.getTilesForTest(path + "/testThree.json");
        toTest = new StraightDirection(6,2,true,true,"");
        printShelf(shelf.get(0));
        assertFalse(toTest.conditionCheck(shelf.get(0)), "Found when shelf is empty");
        printShelf(shelf.get(1));
        assertFalse(toTest.conditionCheck(shelf.get(1)), "Found when not expected");
        printShelf(shelf.get(2));
        assertTrue(toTest.conditionCheck(shelf.get(2)), "Not Found when expected");
        System.out.println("\nTest Three Passsato!");

        shelf = StraightDirectionCGCTest.getTilesForTest(path + "/testFour.json");
        toTest = new StraightDirection(2, Bookshelf.getRows(),false,false,"");
        printShelf(shelf.get(0));
        assertFalse(toTest.conditionCheck(shelf.get(0)), "Found when shelf is empty");
        printShelf(shelf.get(1));
        assertFalse(toTest.conditionCheck(shelf.get(1)), "Found when not expected");
        printShelf(shelf.get(2));
        assertTrue(toTest.conditionCheck(shelf.get(2)), "Not found when expected");
        System.out.println("\nTest Four Passsato!");

        System.out.println("\nTest Passsato!");
    }
}
