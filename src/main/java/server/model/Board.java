package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import observer.Observable;
import observer.Observer;
import org.javatuples.Pair;
import util.Messages.Message;

/**
 * The type Board.
 */
public class Board implements Serializable, Observable<Message> {
    private final ArrayList<CommonGoalCardStrategy> commonGoalCardStrategies;
    private Tile[][] slots;

    /**
     * Instantiates a new Board.
     *
     * @param slots the slots
     */
    public Board(Tile[][] slots) {
        System.out.println("Creating array");
        this.commonGoalCardStrategies = new ArrayList<>(2);
        System.out.println("Choosing first cgc");
        Pair<CommonGoalCardStrategy, CommonGoalCardStrategy> cGcs = CommonGoalCardStrategy.getRandomCards();
        System.out.println("Adding First cgc");
        this.commonGoalCardStrategies.add(cGcs.getValue0());
        System.out.println("Adding second cgc");
        this.commonGoalCardStrategies.add(cGcs.getValue1());
        System.out.println("Saving slots");
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
        int count;
        for (int x = 0; x< slots.length; x++){
            for (int y = 0; y< slots[0].length; y++){
                if (!slots[x][y].Empty()) {
                    count = 0;
                    if ((x != 0 && slots[x - 1][y].Empty())) {
                        count += 1;
                    }
                    if ((x != slots.length - 1 && slots[x + 1][y].Empty())) {
                        count += 1;
                    }
                    if ((y != 0 && slots[x][y - 1].Empty())) {
                        count += 1;
                    }
                    if ((y != slots[0].length - 1 && slots[x][y + 1].Empty())) {
                        count += 1;
                    }
                    slots[x][y].setPickable(count >= 2);
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
