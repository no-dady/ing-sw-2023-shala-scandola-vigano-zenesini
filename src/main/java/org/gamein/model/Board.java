package org.gamein.model;


public class Board {
    private final CommonGoalCard[] commonGoalCards;
    private Tile[][] slots;

    Board(CommonGoalCard[] cards) {
        int rows = 9;
        int cols = 9;
        slots = new Tile[rows][cols];
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
