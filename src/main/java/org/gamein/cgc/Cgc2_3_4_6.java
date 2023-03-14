package org.gamein.cgc;

import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;

import java.awt.print.Book;
import java.util.Arrays;

public class Cgc2_3_4_6 implements CommonGoalCardCondition {
    private int numToLook;
    private int timesToLook;
    private boolean isEqual;
    private boolean isVert;

    public Cgc2_3_4_6(int timesToLook, int numToLook, boolean isEqual, boolean isVert) {
        this.timesToLook = timesToLook;
        this.numToLook = numToLook;
        this.isEqual = isEqual;
        this.isVert = isVert;
    }

    // 4 vertical strips of 4 tiles of the same type
    @Override
    public boolean ConditionCheck(Tile[][] slots) {
        return isVert ? checkColumns(slots) : checkRows(slots);
    }

    private boolean checkColumns(Tile[][] slots) {
        int count = 0, checked = 0;

        for(int i = 0; i < Bookshelf.getCols() && checked < timesToLook; i++) {
            for(int j = 1; j < Bookshelf.getRows() && slots[j][i] != null; ++j) {
                if(count == numToLook) {
                    checked++;
                    break;
                }
                if((slots[j][i].getTileType() == slots[j-1][i].getTileType()) == isEqual) {
                    count++;
                } else {
                    count = 0;
                }
            }
            if (numToLook - checked < Bookshelf.getCols() - i) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRows(Tile[][] slots) {
        int count = 0, checked = 0;

        for(int i = 0; i < Bookshelf.getRows() && checked < timesToLook; i++) {
            for(int j = 1; j < Bookshelf.getCols() && slots[i][j] != null; ++j) {
                if(count == numToLook) {
                    checked++;
                    break;
                }
                if((slots[i][j].getTileType() == slots[i][j-1].getTileType()) == isEqual) {
                    count++;
                } else {
                    count = 0;
                }
            }
            if (numToLook - checked < Bookshelf.getRows() - i) {
                return false;
            }
        }
        return true;
    }
}
