package org.gamein.model;

import java.util.List;

public class Bookshelf {
    private final Tile[][] slots;
    private static final int rows = 6;
    private static final int cols = 5;
    private int tileCount;

    public Bookshelf() {
        slots = new Tile[rows][cols];
        tileCount = 0;
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
            ++tileCount;
            ++i;
        }
    }

    public static int getRows() {
        return rows;
    }
    public static int getCols() {
        return cols;
    }

    public int getTilePerCol(int index, Bookshelf input) { //conta celle libere per la colonna selezionata
        int count = 0;
        for(Tile x: input.getSlots()[index]){
            if(x.getTileId().isEmpty()){
                count++;
            }
        }
        return count;
    }
    /*
     * @return number of tiles in the bookshelf;
     */
    public int getNumTiles() {
        return tileCount;
    }
}
