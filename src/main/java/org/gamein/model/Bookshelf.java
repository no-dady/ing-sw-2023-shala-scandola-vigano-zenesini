package org.gamein.model;

import java.util.List;

public class Bookshelf {
    private final Tile[][] slots;
    private final int rows;

    Bookshelf() {
        rows = 6;
        int cols = 5;
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
}
