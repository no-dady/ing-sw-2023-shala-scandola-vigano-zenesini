package org.gamein.cgc;

import org.gamein.model.Tile;
import org.gamein.model.TileType;

//CROSSDIRECTION
public class Cgc10 implements CommonGoalCardCondition {
    private final int numToLook;
    public Cgc10(int numToLook) {
        this.numToLook = numToLook;
    }
    @Override
    public boolean conditionCheck(Tile[][] shelf) {
        int rowMax = shelf.length - numToLook + 1;
        int columnMax = -1;
        if (rowMax > 0)
        {
            columnMax = shelf[0].length - numToLook + 1;
        }
        for (int row = 0; row < rowMax; row++) {
            for (int column = 0; column < columnMax; column++) {
                if (!shelf[row][column].getTileType().equals(TileType.EMPTY))
                {
                    TileType typeFound = shelf[row][column].getTileType();

                    if (shelf[row + 2][column].getTileType().equals(typeFound) && shelf[row + 1][column + 1].getTileType().equals(typeFound) && shelf[row][column + 2].getTileType().equals(typeFound) && shelf[row + 2][column + 2].getTileType().equals(typeFound))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
