package org.gamein.model;

import org.javatuples.Triplet;

import java.util.List;

public class PersonalGoalCard {
    private List<Triplet<Integer, Integer, Tile>> conditions;


    public List<Triplet<Integer, Integer, Tile>> getConditions() {
        return this.conditions;
    }
}
