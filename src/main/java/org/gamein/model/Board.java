package org.gamein.model;


public class Board {
    private CommonGoalCard[] commonGoalCards;
    private Tile[][] slots;

    Board() {
        int rows = 9;
        int cols = 9;
        slots = new Tile[rows][cols];
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


    public void setCommonGoalCards(CommonGoalCard[] commonGoalCards) {
        this.commonGoalCards = commonGoalCards;
    }
}
