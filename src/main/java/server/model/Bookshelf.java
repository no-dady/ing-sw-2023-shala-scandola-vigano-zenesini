package server.model;

import java.io.Serializable;
import java.util.List;

/**
 * The type Bookshelf.
 */
public class Bookshelf implements Serializable {
    private final Tile[][] slots;
    private static final int rows = 6;
    private static final int cols = 5;
    private int tileCount;

    /**
     * Instantiates a new Bookshelf.
     */
    public Bookshelf() {
        slots = new Tile[rows][cols];
        tileCount = 0;
    }

    /**
     * Get slots tile [ ] [ ].
     *
     * @return the tile [ ] [ ]
     */
    public Tile[][] getSlots() {
        return slots;
    }

    /**
     * Sets slots.
     *
     * @param column        the column
     * @param selectedTiles the selected tiles
     */
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

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public static int getRows() {
        return rows;
    }

    /**
     * Gets cols.
     *
     * @return the cols
     */
    public static int getCols() {
        return cols;
    }

    public int getEmptyTilesColumn(int index) {
    /**
     * Gets tile per col.
     *
     * @param index the index
     * @return the tile per col
     **/
        int count = 0;
        for(Tile x: this.getSlots()[index]){
            if(x.getTileId().isEmpty()){
                count++;
            }
        }
        return count;
    }

    /**
     * Gets num tiles.
     *
     * @return the num tiles
     */
    public int getNumTiles() {
        return tileCount;
    }
}
