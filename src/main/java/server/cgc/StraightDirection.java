package server.cgc;

import server.model.Bookshelf;
import server.model.Tile;

// TODO: Test algorithms for performance and correctness
public class StraightDirection implements CommonGoalCardCondition {
    private int numToLook;
    private int timesToLook;
    private boolean isEqual;
    private boolean isVert;

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
        int count, checked = 0;

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
        int count, checked = 0;

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
