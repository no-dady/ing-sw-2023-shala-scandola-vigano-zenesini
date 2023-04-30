package server.model;

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
