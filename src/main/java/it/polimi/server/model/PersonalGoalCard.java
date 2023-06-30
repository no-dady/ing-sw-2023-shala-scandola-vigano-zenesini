package it.polimi.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polimi.observer.Observable;
import it.polimi.observer.Observer;
import it.polimi.util.Messages.Message;

/**
 * The type Personal goal card.
 *
 * @author daniel
 * @version $Id: $Id
 */
public class PersonalGoalCard implements Serializable, Observable<Message> {
    private final Map<String, Coordinates> goals;
    private final String fileName;

    /**
     * Instantiates a new Personal goal card.
     *
     * @param goals     the goals
     * @param fileName a {@link java.lang.String} object
     */
    public PersonalGoalCard(Map<String, Coordinates> goals, String fileName) {
        this.goals = goals;
        this.fileName = fileName;
    }

    /**
     * <p>Getter for the field <code>fileName</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getFileName() {
        return fileName;

    }

    /**
     * Gets coordinates.
     *
     * @param goal the goal
     * @return the coordinates
     */
    public Coordinates getCoordinates(String goal) {
        return goals.getOrDefault(goal, null);
    }

    /**
     * Completed boolean.
     *
     * @param slots the slots
     * @return the boolean
     */
    public int completed(Tile[][] slots) {
        int count= 0;
        for(String key : goals.keySet()) {
            var coord = goals.get(key);
            if(!(slots[coord.x()][coord.y()] == null) && !slots[coord.x()][coord.y()].Empty() && slots[coord.x()][coord.y()].getTileType().equals(key)){
                count ++;
            }
        }

        return count;
    }

    /**
     * <p>getPoints.</p>
     *
     * @param player a {@link it.polimi.server.model.Player} object
     * @return a int
     */
    public int getPoints(Player player){
        int total = 0;
        return switch (completed(player.getBookshelf().getSlots())) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 6;
            case 5 -> 9;
            case 6 -> 12;
            default -> total;
        };
    }

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
    public void notify(Message move) {
        synchronized (observers) {
            for(Observer<Message> observer : observers){
                observer.update(move);
            }
        }
    }
}
