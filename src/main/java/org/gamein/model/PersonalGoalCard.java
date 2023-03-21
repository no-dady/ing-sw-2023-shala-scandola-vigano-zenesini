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

    public PersonalGoalCard(Pair<Integer, Integer> red, Pair<Integer, Integer> cyan, Pair<Integer, Integer> white, Pair<Integer, Integer> orange, Pair<Integer, Integer> blue, Pair<Integer, Integer> pink) {
        this.red = red;
        this.cyan = cyan;
        this.white = white;
        this.orange = orange;
        this.blue = blue;
        this.pink = pink;
    }


}
