package it.polimi.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import it.polimi.observer.Observer;
import it.polimi.util.Messages.Message;
import it.polimi.observer.Observable;

/**
 * The type Pocket.
 */
public class Pocket implements Serializable, Observable<Message> {
    private final ArrayList<Tile> tileList;

    /**
     * Instantiates a new Pocket.
     */
    public Pocket() {
        this.tileList = new ArrayList<Tile>();
    }

    /**
     * Instantiates a new Pocket.
     *
     * @param tiles the tiles
     */
    public Pocket(ArrayList<Tile> tiles) {

        this.tileList = tiles;
    }

    /**
     * Gets left.
     *
     * @return the left
     */
    public int getLeft() {
        return this.tileList.size();
    }

    /**
     * Pop tiles array list.
     *
     * @param n the n
     * @return the array list
     */
    public ArrayList<Tile> popTiles(int n){
        ArrayList<Tile> poppedTiles = new ArrayList<>(tileList.subList(0,n));
        tileList.subList(0,n).clear();
        return poppedTiles;
    }


    private transient final List<Observer<Message>> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer<Message> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    @Override
    public void notify(Message message) {
        synchronized (observers) {
            for(Observer<Message> observer : observers){
                observer.update(message);
            }
        }
    }

}
