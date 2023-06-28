package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import observer.Observable;
import observer.Observer;
import util.Messages.Message;

/**
 * The type Bookshelf.
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
        for (Tile[] tilecolumn: slots
             ) {
            for (Tile t: tilecolumn
                 ) { t = new Tile();

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
     **/
    public int getEmptyTilesColumn(int index) {

        int count = 0;
        for (int j = 0; j < rows ; j ++){

            if ((slots[j][index] == null || slots[j][index].Empty())){
                count ++;
            }
        }
        return count;
    }
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

    /**
     *
     * @param observer of type Observer<Message>: the observer to add
     */
    @Override
    public void addObserver(Observer<Message> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /**
     *
     * @param message of type Message: the notifying message
     */
    @Override
    public void notify(Message message) {
        synchronized (observers) {
            for(Observer<Message> observer : observers){
                observer.update(message);
            }
        }
    }

    public boolean isFull() {
        return tileCount == rows * cols;
    }


}
