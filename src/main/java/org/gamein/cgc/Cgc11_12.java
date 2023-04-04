package org.gamein.cgc;

import org.gamein.model.Tile;
import org.gamein.model.TileType;

//TODO MAYBE WE COULD SEPARATE THE 11TH AND THE 12TH INTO TWO SEPARATE OBJECT USING THE .size OF
//THE COLUMN TO CALCULATE THE 12TH CARD
//DIAGONALDIRECTION
public class Cgc11_12 implements CommonGoalCardCondition{
    private final int numToLook;
    private final boolean isEleven;

    public Cgc11_12(int numToLook, boolean isEleven) {
        this.numToLook = numToLook;
        this.isEleven = isEleven;
    }

    @Override
    public boolean conditionCheck(Tile[][] slots) {
        int rowMax = slots.length - numToLook;
        boolean isRising = false;
        boolean isDiagonal = false;

        for (int row = 0; row <= rowMax; row++)
        {
            if (!slots[row][0].getTileType().equals(TileType.EMPTY)) {
                if (!slots[row + 1][1].getTileType().equals(TileType.EMPTY)) {
                    if (!slots[row + 2][2].getTileType().equals(TileType.EMPTY)) {
                        if (!slots[row + 3][3].getTileType().equals(TileType.EMPTY)) {
                            if (!slots[row + 4][4].getTileType().equals(TileType.EMPTY)) {
                                isRising = true;
                                isDiagonal = true;
                                rowMax = row;
                                break;
                            }
                        }
                    }
                }
            }
            if (!slots[row][4].getTileType().equals(TileType.EMPTY)) {
                if (!slots[row + 1][3].getTileType().equals(TileType.EMPTY)) {
                    if (!slots[row + 2][2].getTileType().equals(TileType.EMPTY)) {
                        if (!slots[row + 3][1].getTileType().equals(TileType.EMPTY)) {
                            if (!slots[row + 4][0].getTileType().equals(TileType.EMPTY)) {
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

        if (isDiagonal)
        {
            if (isEleven)
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
                    if (slots[rowMax + 1][0].getTileType().equals(TileType.EMPTY)) {
                        if (slots[rowMax + 2][1].getTileType().equals(TileType.EMPTY)) {
                            if (slots[rowMax + 3][2].getTileType().equals(TileType.EMPTY)) {
                                if (slots[rowMax + 4][3].getTileType().equals(TileType.EMPTY)) {
                                    if (rowMax == 1) {
                                        return true;
                                    }
                                    else {
                                        if (slots[rowMax + 5][3].getTileType().equals(TileType.EMPTY)) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    if (slots[rowMax + 1][4].getTileType().equals(TileType.EMPTY)) {
                        if (slots[rowMax + 2][3].getTileType().equals(TileType.EMPTY)) {
                            if (slots[rowMax + 3][2].getTileType().equals(TileType.EMPTY)) {
                                if (slots[rowMax + 4][1].getTileType().equals(TileType.EMPTY)) {
                                    if (rowMax == 1) {
                                        return true;
                                    }
                                    else {
                                        if (slots[rowMax + 5][0].getTileType().equals(TileType.EMPTY)) {
                                            return true;
                                        }
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
