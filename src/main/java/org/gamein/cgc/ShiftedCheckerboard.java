package org.gamein.cgc;

import org.gamein.model.Tile;
import org.gamein.model.TileType;

public class ShiftedCheckerboard implements CommonGoalCardCondition{

    @Override
    public boolean conditionCheck(Tile[][] shelf) {
        int p=0;
        for (int k = 0; k < 2; k++){
            if (shelf[k][p].getTileType() != TileType.EMPTY && shelf[k][p+2].getTileType() != TileType.EMPTY && shelf[k][p+4].getTileType() != TileType.EMPTY && shelf[k+2][p].getTileType() != TileType.EMPTY && shelf[k+2][p+2].getTileType() != TileType.EMPTY && shelf[k+2][p+4].getTileType() != TileType.EMPTY && shelf[k+4][p+3].getTileType() != TileType.EMPTY && shelf[k+4][p+1].getTileType() != TileType.EMPTY && shelf[k][p].getTileType() == shelf[k][p+2].getTileType() && shelf[k][p+2].getTileType() == shelf[k][p+4].getTileType() && shelf[k][p+4].getTileType() == shelf[k+2][p].getTileType() && shelf[k+2][p].getTileType() == shelf[k+2][p+2].getTileType() && shelf[k+2][p+2].getTileType() == shelf[k+2][p+4].getTileType() && shelf[k+2][p+4].getTileType() == shelf[k+4][p+1].getTileType() && shelf[k+4][p+1].getTileType() == shelf[k+4][p+3].getTileType())
            {
                return true;
            }
        }
        return false;
    }
}
