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
}
