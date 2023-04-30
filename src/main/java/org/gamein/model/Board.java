package org.gamein.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {
    private final ArrayList<CommonGoalCard> commonGoalCards;
    private Tile[][] slots;

    public Board(CommonGoalCard commonGoalStrategy, Tile[][] slots) {
        this.commonGoalCards = new ArrayList<>(2);
        this.commonGoalCards.add(commonGoalStrategy.getRandGoalCard());
        this.commonGoalCards.add(commonGoalStrategy.getRandGoalCard());
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

    public ArrayList<CommonGoalCard> getCommonGoalCards() {
        return commonGoalCards;
    }

}
