package it.polimi.server.controller;

import com.google.gson.reflect.TypeToken;
import it.polimi.server.cgc.StraightDirectionCGCTest;
import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.Tile;
import it.polimi.util.Parser;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CalcShelfPointsTest {


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
    @Test
    void calculateBookshelfPoints() throws IOException {
        String path = "src/test/resources/json/shelves/StraightDirectionTest";

        List<Tile[][]> shelf = StraightDirectionCGCTest.getTilesForTest(path + "/testOne.json");
        assertEquals(0, calculateBookshelfPoints(shelf.get(0)));
        assertEquals(14, calculateBookshelfPoints(shelf.get(1)));
        assertEquals(8, calculateBookshelfPoints(shelf.get(2)));
    }

    private boolean isValidTileBookshelf(int row, int col) {
        return row >= 0 && row < Bookshelf.getRows() && col >= 0 && col < Bookshelf.getCols();
    }
    private int exploreAdjacentTiles(int row, int col, boolean[][] visited, Tile[][] slots) {
        int consecutiveTiles = 1;
        Tile currentTile = slots[row][col];
        visited[row][col] = true;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            if (isValidTileBookshelf(newRow, newCol) && !visited[newRow][newCol]) {
                Tile adjacentTile = slots[newRow][newCol];
                if (adjacentTile != null && adjacentTile.equals(currentTile)) {
                    consecutiveTiles += exploreAdjacentTiles(newRow, newCol, visited, slots);
                }
            }
        }
        return consecutiveTiles;
    }
    private int calculateBookshelfPoints(Tile[][] bookshelf){
        int points = 0;
        Tile[][] slots = bookshelf;
        boolean[][] visited = new boolean[Bookshelf.getRows()][Bookshelf.getCols()];
        for (int i = 0; i < Bookshelf.getRows(); i++) {
            for (int j = 0; j < Bookshelf.getCols(); j++) {
                if (slots[i][j] != null && !visited[i][j]) {
                    int consecutiveTiles = exploreAdjacentTiles(i, j, visited, slots);
                    if (consecutiveTiles >= 6) {
                        points += 8;
                    } else if (consecutiveTiles == 5) {
                        points += 5;
                    } else if (consecutiveTiles == 4) {
                        points += 3;
                    } else if (consecutiveTiles == 3) {
                        points += 2;
                    }
                }
            }
        }
        return points;
    }
}
