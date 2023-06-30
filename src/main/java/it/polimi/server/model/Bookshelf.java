package it.polimi.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.observer.Observable;
import it.polimi.observer.Observer;
import it.polimi.util.Messages.Message;

/**
 * The type Bookshelf.
 *
 * @author daniel
 * @version $Id: $Id
 */
public class Bookshelf implements Serializable, Observable<Message> {
    private final Tile[][] slots;
    private static final int rows = 6;
    private static final int cols = 5;
    private int tileCount;

    /**
     * Instantiates a new Bookshelf.
     */
    public Bookshelf() {
        slots = new Tile[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                slots[i][j] = new Tile();
            }
        }
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
        while(i < rows && slots[i][column] != null && !slots[i][column].Empty()) {
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
    /**
     * Gets tile per col.
     *
     * @param index the index
     * @return the tile per col
     */
    public int getEmptyTilesColumn(int index) {

        int count = 0;
        for (int j = 0; j < rows ; j ++){

            if ((slots[j][index] == null || slots[j][index].Empty())){
                count ++;
            }
        }
        return count;
    }
    /**
     * <p>lastTileOnColumnIndex.</p>
     *
     * @param index a int
     * @return a int
     */
    public int lastTileOnColumnIndex(int index){
        for (int j = 0; j < rows ; j ++){
            if (slots[j][index] == null || slots[j][index].Empty()){
                return j;
            }
        }
        return (Bookshelf.getRows() - 1) ;
    }

    /**
     * Gets num tiles.
     *
     * @return the num tiles
     */
    public int getNumTiles() {
        return tileCount;
    }
     //Observable implementation
    private transient final List<Observer<Message>> observers = new ArrayList<>();

    /** {@inheritDoc} */
    @Override
    public void addObserver(Observer<Message> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void notify(Message message) {
        synchronized (observers) {
            for(Observer<Message> observer : observers){
                observer.update(message);
            }
        }
    }

    /**
     * <p>isFull.</p>
     *
     * @return a boolean
     */
    public boolean isFull() {
        return tileCount == rows * cols;
    }


}
