package org.gamein.cgc;

import org.gamein.model.Tile;
import org.gamein.model.TileType;

public class Cgc11_12 implements CommonGoalCardCondition{
    @Override
    public boolean ConditionCheck(Tile[][] slots, int numToLook, boolean isEqual, boolean isVert) {
        int rowMax = slots.length - numToLook;
        boolean isRising = false;
        boolean isDiagonal = false;

        for (int row = 0; row <= rowMax; row++)
        {
            if (slots[row][0] != null) {
                if (slots[row + 1][1] != null) {
                    if (slots[row + 2][2] != null) {
                        if (slots[row + 3][3] != null) {
                            if (slots[row + 4][4] != null) {
                                isRising = true;
                                isDiagonal = true;
                                rowMax = row;
                                break;
                            }
                        }
                    }
                }
            }
            else if (slots[row][4] != null) {
                if (slots[row + 1][3] != null) {
                    if (slots[row + 2][2] != null) {
                        if (slots[row + 3][1] != null) {
                            if (slots[row + 4][0] != null) {
                                isRising = false;
                                isDiagonal = true;
                                rowMax = row;
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (isEqual)
        {
            if (isRising) {
                TileType typeFound = slots[rowMax][0].getTileType();
                if (slots[rowMax + 1][1].getTileType().equals(typeFound)) {
                    if (slots[rowMax + 2][2].getTileType().equals(typeFound)) {
                        if (slots[rowMax + 3][3].getTileType().equals(typeFound)) {
                            if (slots[rowMax + 4][4].getTileType().equals(typeFound)) {
                                return true;
                            }
                        }
                    }
                }
            }
            else {
                TileType typeFound = slots[rowMax][4].getTileType();
                if (slots[rowMax + 1][3].getTileType().equals(typeFound)) {
                    if (slots[rowMax + 2][2].getTileType().equals(typeFound)) {
                        if (slots[rowMax + 3][1].getTileType().equals(typeFound)) {
                            if (slots[rowMax + 4][0].getTileType().equals(typeFound)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        else {
            if (isRising)
            {
                if (slots[rowMax + 1][0] == null) {
                    if (slots[rowMax + 2][1] == null) {
                        if (slots[rowMax + 3][2] == null) {
                            if (slots[rowMax + 4][3] == null) {
                                if (rowMax == 1) {
                                    return true;
                                }
                                else {
                                    if (slots[rowMax + 5][3] == null) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else {
                if (slots[rowMax + 1][4] == null) {
                    if (slots[rowMax + 2][3] == null) {
                        if (slots[rowMax + 3][2] == null) {
                            if (slots[rowMax + 4][1] == null) {
                                if (rowMax == 1) {
                                    return true;
                                }
                                else {
                                    if (slots[rowMax + 5][0] == null) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}
