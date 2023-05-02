package org.gamein.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Board.
 */
public class Board implements Serializable {
    private final ArrayList<CommonGoalCardStrategy> commonGoalCardStrategies;
    private Tile[][] slots;

    /**
     * Instantiates a new Board.
     *
     * @param slots the slots
     */
    public Board(Tile[][] slots) {
        this.commonGoalCardStrategies = new ArrayList<>(2);
        this.commonGoalCardStrategies.add(CommonGoalCardStrategy.getRandomCard());
        this.commonGoalCardStrategies.add(CommonGoalCardStrategy.getRandomCard());
        this.slots = slots;
    }

    /**
     * Fill board.
     *
     * @param tiles the tiles
     */
    public void fillBoard(Tile[][] tiles) {

        slots = tiles;
    }

    /**
     * Remove tile.
     *
     * @param x the x
     * @param y the y
     */
    public void removeTile(int x, int y) {
        slots[x][y] = new Tile();
    }

    /**
     * Get slots tile [ ] [ ].
     *
     * @return the tile [ ] [ ]
     */
    public Tile[][] getSlots() {
        return slots;
    }

    /**
     * Gets tile.
     *
     * @param x the x
     * @param y the y
     * @return the tile
     */
    public Tile getTile(int x, int y) {
        return slots[x][y];
    }

    /**
     * Gets common goal cards.
     *
     * @return the common goal cards
     */
    public ArrayList<CommonGoalCardStrategy> getCommonGoalCards() {
        return commonGoalCardStrategies;
    }

}
