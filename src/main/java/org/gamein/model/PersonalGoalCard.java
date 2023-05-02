package org.gamein.model;

import java.util.Map;

/**
 * The type Personal goal card.
 */
public class PersonalGoalCard {
    private Map<String, Coordinates> goals;

    /**
     * Instantiates a new Personal goal card.
     *
     * @param goals the goals
     */
    public PersonalGoalCard(Map<String, Coordinates> goals) {
        this.goals = goals;
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
    public boolean completed(Tile[][] slots) {
        for(String key : goals.keySet()) {
            var coord = goals.get(key);

            if(!slots[coord.x()][coord.y()].getTileType().equals(key)){
                return false;
            }
        }

        return true;
    }
}
