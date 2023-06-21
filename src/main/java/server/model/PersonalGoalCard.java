package server.model;

import java.util.Map;

/**
 * The type Personal goal card.
 */
public class PersonalGoalCard {
    private final Map<String, Coordinates> goals;
    public String fileName;

    /**
     * Instantiates a new Personal goal card.
     *
     * @param goals     the goals
     */

    public PersonalGoalCard(Map<String, Coordinates> goals, String fileName) {
        this.goals = goals;
        this.fileName = fileName;
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
            if(slots[coord.x()][coord.y()].getTileType().equals(key)){
                count ++;
            }
        }

        return count;
    }
}
