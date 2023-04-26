package org.gamein.model;

import java.util.Map;

public class PersonalGoalCard {
    private Map<String, Coordinates> goals;

    public PersonalGoalCard(Map<String, Coordinates> goals) {
        this.goals = goals;
    }

    /*
     * @returns the Coordinates object mapped to the goal key
     * @param goal not null
     */
    public Coordinates getCoordinates(String goal) {
        return goals.getOrDefault(goal, null);
    }

    /*
     * @returns true if all goals are completed
     * @param shelf: player bookshelf not null
     */
    public boolean completed(Bookshelf shelf) {
        var slots = shelf.getSlots();
        for(String key : goals.keySet()) {
            var coord = goals.get(key);

            if(slots[coord.x()][coord.y()].getTileType() != TileType.valueOf(key)) {
                return false;
            }
        }

        return true;
    }
}
