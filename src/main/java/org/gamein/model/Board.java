package org.gamein.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {
    private final ArrayList<CommonGoalCardStrategy> commonGoalCardStrategies;
    private Tile[][] slots;

    public Board(Tile[][] slots) {
        this.commonGoalCardStrategies = new ArrayList<>(2);
        this.commonGoalCardStrategies.add(CommonGoalCardStrategy.getRandomCard());
        this.commonGoalCardStrategies.add(CommonGoalCardStrategy.getRandomCard());
        this.slots = slots;
    }

    public void fillBoard(Tile[][] tiles) {

        slots = tiles;
    }

    public void removeTile(int x, int y) {
        slots[x][y] = new Tile();
    }

    public Tile[][] getSlots() {
        return slots;
    }

    public Tile getTile(int x, int y) {
        return slots[x][y];
    }

    public ArrayList<CommonGoalCardStrategy> getCommonGoalCards() {
        return commonGoalCardStrategies;
    }

}
