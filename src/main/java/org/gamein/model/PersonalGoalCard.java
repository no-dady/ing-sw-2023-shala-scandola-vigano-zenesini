package org.gamein.model;

import org.javatuples.Triplet;

import java.util.List;

public class PersonalGoalCard {
    private final Triplet<Integer, Integer, TileType>[] conditions;

    public PersonalGoalCard(Triplet<Integer, Integer, TileType>[] conditions) {
        this.conditions = conditions;
    }

    public Triplet<Integer, Integer, TileType> getConditions(TileType tile) {
        return this.conditions[tile.ordinal()];
    }
}
