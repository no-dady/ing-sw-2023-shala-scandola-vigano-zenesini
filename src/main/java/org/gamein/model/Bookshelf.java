package org.gamein.model;

import java.util.List;

public class Bookshelf {
    private final Tile[][] slots;
    private static final int rows = 6;
    private static final int cols = 5;

    Bookshelf() {
        slots = new Tile[rows][cols];
    }

    public Tile[][] getSlots() {
        return slots;
    }

    public void setSlots(int column, List<Tile> selectedTiles) {
        int i = 0;
        while(i < rows && slots[i][column] != null) {
            ++i;
        }

        for(Tile t : selectedTiles) {
            slots[i][column] = t;
            ++i;
        }
    }

    public static int getRows() {
        return rows;
    }
    public static int getCols() {
        return cols;
    }
}
