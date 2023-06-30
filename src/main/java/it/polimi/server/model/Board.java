package it.polimi.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.observer.Observable;
import it.polimi.observer.Observer;
import it.polimi.util.Messages.Message;

/**
 * The type Board.
 */
public class Board implements Serializable, Observable<Message> {
    private final ArrayList<CommonGoalCardStrategy> commonGoalCardStrategies = new ArrayList<>(2);
    private Tile[][] slots;

    /**
     * Instantiates a new Board.
     *
     * @param slots the slots
     */

    public Board(Tile[][] slots) {
        this.slots = slots;
    }

    public Board(Tile[][] slots, int numPlayers){
        //System.out.println("Creating array");
        //System.out.println("Adding cgc");
        commonGoalCardStrategies.addAll(CommonGoalCardStrategy.getRandomCards());
        for (CommonGoalCardStrategy c: commonGoalCardStrategies) {
            c.setNumPlayers(numPlayers);
        }
        //System.out.println("Saving slots");
        this.slots = slots;
    }

    /**
     * Fill board.
     *
     * @param tiles the tiles
     */
    public void fillBoard(Tile[][] tiles) {
        slots = tiles;
    }

    /**
     * Remove tile.
     *
     * @param x the x
     * @param y the y
     */
    public void removeTile(int x, int y) {
        slots[x][y] = new Tile();
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
     * Gets tile.
     *
     * @param x the x
     * @param y the y
     * @return the tile
     */
    public Tile getTile(int x, int y) {
        return slots[x][y];
    }

    /**
     * Gets common goal cards.
     *
     * @return the common goal cards
     */
    public ArrayList<CommonGoalCardStrategy> getCommonGoalCards() {
        return commonGoalCardStrategies;
    }
    public void updatePickable() {
        int rows = slots.length;
        int cols = slots[0].length;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (!slots[x][y].Empty()) {
                    slots[x][y].setPickable(((x > 0 && slots[x - 1][y].Empty()) || (x < rows - 1 && slots[x + 1][y].Empty()) || (y > 0 && slots[x][y - 1].Empty()) || (y < cols - 1 && slots[x][y + 1].Empty())));
                }
            }
        }
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
