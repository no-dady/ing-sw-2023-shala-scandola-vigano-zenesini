package org.gamein.model;


public class Board {
    private final CommonGoalCard[] commonGoalCards;
    private Tile[][] slots;

    Board(CommonGoalCard[] cards, Tile[][] slots) {
        this.slots = slots;
        commonGoalCards = cards;
    }

    public void fillBoard(Tile[][] tiles) {
        slots = tiles;
    }

    public Tile[][] getSlots() {
        return slots;
    }

    public CommonGoalCard[] getCommonGoalCards() {
        return commonGoalCards;
    }


}
