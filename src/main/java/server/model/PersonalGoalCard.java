package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import observer.Observable;
import observer.Observer;
import util.Messages.Message;

/**
 * The type Personal goal card.
 */
public class PersonalGoalCard implements Serializable, Observable<Message> {
    private final Map<String, Coordinates> goals;
    private String fileName;

    /**
     * Instantiates a new Personal goal card.
     *
     * @param goals     the goals
     */

    public PersonalGoalCard(Map<String, Coordinates> goals, String fileName) {
        this.goals = goals;
        this.fileName = fileName;
    }

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

    private transient final List<Observer<Message>> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer<Message> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    @Override
    public void notify(Message move) {
        synchronized (observers) {
            for(Observer<Message> observer : observers){
                observer.update(move);
            }
        }
    }
}
