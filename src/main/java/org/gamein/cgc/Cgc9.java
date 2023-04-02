package org.gamein.cgc;

import org.gamein.model.Tile;

public class Cgc9 implements CommonGoalCardCondition{

    @Override
    public boolean conditionCheck(Tile[][] shelf) {
        int k=0;
        for (int p = 0; p < 2; p++){
            if (shelf[k][p] != null && shelf[k][p+2] != null && shelf[k][p+4] != null && shelf[k+2][p] != null && shelf[k+2][p+2] != null && shelf[k+2][p+4] != null && shelf[k+4][p+3] != null && shelf[k+4][p+5] != null && shelf[k][p].getTileType() == shelf[k][p+2].getTileType() && shelf[k][p+2].getTileType() == shelf[k][p+4].getTileType() && shelf[k][p+4].getTileType() == shelf[k+2][p].getTileType() && shelf[k+2][p].getTileType() == shelf[k+2][p+2].getTileType() && shelf[k+2][p+2].getTileType() == shelf[k+2][p+4].getTileType() && shelf[k+2][p+4].getTileType() == shelf[k+6][p+3].getTileType() && shelf[k+6][p+3].getTileType() == shelf[k+6][p+5].getTileType())
            {
                return true;
            }
        }
        return false;
    }
}
