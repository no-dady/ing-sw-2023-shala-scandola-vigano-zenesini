package org.gamein.model;

import org.javatuples.Pair;

import java.util.Dictionary;
import java.util.List;

public class PersonalGoalCard {
    private Pair<Integer, Integer> red;
    private Pair<Integer, Integer> cyan;
    private Pair<Integer, Integer> white;
    private Pair<Integer, Integer> orange;
    private Pair<Integer, Integer> blue;
    private Pair<Integer, Integer> pink;

    public PersonalGoalCard(Triplet<Integer, Integer, TileType>[] conditions) {
        this.conditions = conditions;
    }

    public Triplet<Integer, Integer, TileType> getConditions(TileType tile) {
        return this.conditions[tile.ordinal()];
    }
}
