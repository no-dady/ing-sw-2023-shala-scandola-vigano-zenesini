package org.gamein.model;


public class Board {
    private final CommonGoalCard[] commonGoalCards;
    private Tile[][] slots;

    public Board(CommonGoalCard[] cards, Tile[][] slots) {
        this.slots = slots;
        commonGoalCards = cards;
    }

    public void fillBoard(Tile[][] tiles) {
        slots = tiles;
    }

    public Tile[][] getSlots() {
        return slots;
    }

    public Tile getTile(int x, int y){ return slots[x][y];}

    public CommonGoalCard[] getCommonGoalCards() {
        return commonGoalCards;
    }

}
