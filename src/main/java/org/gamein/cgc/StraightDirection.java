package org.gamein.cgc;

import org.gamein.model.Bookshelf;
import org.gamein.model.CommonGoalCardStrategy;
import org.gamein.model.Tile;

/**
 * The type Straight direction.
 */
// TODO: Test algorithms for performance and correctness
public class StraightDirection extends CommonGoalCardStrategy {
    private int numToLook;
    private int timesToLook;
    private boolean isEqual;
    private boolean isVert;

    /**
     * Instantiates a new Straight direction.
     *
     * @param timesToLook the times to look
     * @param numToLook   the num to look
     * @param isEqual     the is equal
     * @param isVert      the is vert
     */
    public StraightDirection(int timesToLook, int numToLook, boolean isEqual, boolean isVert) {
        this.timesToLook = timesToLook;
        this.numToLook = numToLook;
        this.isEqual = isEqual;
        this.isVert = isVert;
    }

    // 4 vertical strips of 4 tiles of the same type
    @Override
    public boolean conditionCheck(Tile[][] slots) {
        return isVert ? checkColumns(slots) : checkRows(slots);
    }

    private boolean checkColumns(Tile[][] slots) {
        int count = 0, checked = 0;

        for(int i = 0; i < Bookshelf.getCols() && checked < timesToLook; i++) {
            var tile = slots[0][i];
            count = 0;
            for(int j = 0; j < Bookshelf.getRows() && !slots[j][i].Empty() && count < numToLook; ++j) {
                if((slots[j][i].getTileType().equals(tile.getTileType())) == isEqual) {
                    count++;
                } else {
                    tile = slots[j][i];
                    count = 0;
                }
            }
            if(count == numToLook) {
                checked++;
            }

        }

        return (checked == timesToLook);
    }

    private boolean checkRows(Tile[][] slots) {
        int count = 0, checked = 0;

        for(int i = 0; i < Bookshelf.getRows() && checked < timesToLook; i++) {
            var tile = slots[i][0];
            count = 0;
            for(int j = 0; j < Bookshelf.getCols() && !slots[i][j].Empty() && count < numToLook; ++j) {
                if((slots[i][j].getTileType().equals(tile.getTileType())) == isEqual) {
                    count++;
                } else {
                    tile = slots[i][j];
                    count = 0;
                }
            }
            if(count == numToLook) {
                checked++;
            }

        }

        return (checked == timesToLook);
    }
}
