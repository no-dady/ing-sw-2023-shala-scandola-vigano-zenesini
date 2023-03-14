package org.gamein.cgc;

import org.gamein.model.Tile;

import java.util.Arrays;

public class Cgc1_8 implements CommonGoalCardCondition {

    public boolean indexEqual(Tile[] toCheck){
        if (toCheck[0] != null && toCheck[1] != null && toCheck[2] != null && toCheck[3] != null)
            if (toCheck[0].getTileType() == toCheck[1].getTileType() && toCheck[1].getTileType() == toCheck[2].getTileType() && toCheck[2].getTileType() == toCheck[3].getTileType()) {
                return true;
            }
        return false;
    }

    @Override
    public boolean ConditionCheck(Tile[][] shelf, int numToLook, boolean isEqual, boolean isVert) {
        int count = 0;
        Tile[] corners = new Tile[4];
        corners[0] = shelf[0][0];
        corners[1] = shelf[5][6];
        corners[2] = shelf[0][6];
        corners[3] = shelf[5][0];

        if(numToLook == 0){
            return indexEqual(corners);
        }
        else {
            for (int row = 0; row < shelf[0].length - 2 && count < 2; row++) {
                for (int col = 0; col < shelf.length - 1 && count < 2; col++) {
                    corners[0] = shelf[row][col];
                    corners[1] = shelf[row + 1][col];
                    corners[2] = shelf[row][col + 1];
                    corners[3] = shelf[row + 1][col + 1];
                    if (indexEqual(corners)) {
                        count++;
                    }
                }
            }
            if (count == 2) return true;
            return false;
        }
    }
}
