package org.gamein.model;

public class Board {
    private CommonGoalCard[] commonGoalCards;
    private Tile[][] slots;

    Board() {
        slots = new Tile[9][9];
    }

    public void fillBoard(Tile[][] tiles) {
        slots = tiles.clone();
    }

    public Tile[][] getSlots() {
        return slots;
    }
}
