package org.gamein.model;

import java.io.Serializable;

public class Board implements Serializable {
    private final CommonGoalCard[] commonGoalCards;
    private Tile[][] slots;

    public Board(CommonGoalCard[] cards, Tile[][] slots) {
        this.slots = slots;
        commonGoalCards = cards;
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

    public CommonGoalCard[] getCommonGoalCards() {
        return commonGoalCards;
    }

}
