package org.gamein.model;

import java.util.List;

public class Bookshelf {
    private final Tile[][] slots;
    private static final int rows = 6;
    private static final int cols = 5;

    public Bookshelf() {
        slots = new Tile[rows][cols];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                slots[i][j]= new Tile();
            }
        }
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
