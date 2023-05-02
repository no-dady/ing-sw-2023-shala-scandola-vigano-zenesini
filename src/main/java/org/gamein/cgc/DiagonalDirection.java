package org.gamein.cgc;

import org.gamein.model.CommonGoalCardStrategy;
import org.gamein.model.Tile;

/**
 * The type Diagonal direction.
 */
//TODO MAYBE WE COULD SEPARATE THE 11TH AND THE 12TH INTO TWO SEPARATE OBJECT USING THE .size OF
//THE COLUMN TO CALCULATE THE 12TH CARD
//DIAGONALDIRECTION
public class DiagonalDirection extends CommonGoalCardStrategy {
    private final int numToLook;
    private final boolean isEleven;

    /**
     * Instantiates a new Diagonal direction.
     *
     * @param numToLook the num to look
     * @param isEleven  the is eleven
     */
    public DiagonalDirection(int numToLook, boolean isEleven) {
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
            if (!slots[row][0].Empty()) {
                if (!slots[row + 1][1].Empty()) {
                    if (!slots[row + 2][2].Empty()) {
                        if (!slots[row + 3][3].Empty()) {
                            if (!slots[row + 4][4].Empty()) {
                                isRising = true;
                                isDiagonal = true;
                                rowMax = row;
                                break;
                            }
                        }
                    }
                }
            }
            if (!slots[row][4].Empty()) {
                if (!slots[row + 1][3].Empty()) {
                    if (!slots[row + 2][2].Empty()) {
                        if (!slots[row + 3][1].Empty()) {
                            if (!slots[row + 4][0].Empty()) {
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
                    String typeFound = slots[rowMax][0].getTileType();
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
                    String typeFound = slots[rowMax][4].getTileType();
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
                    if (slots[rowMax + 1][0].Empty()) {
                        if (slots[rowMax + 2][1].Empty()) {
                            if (slots[rowMax + 3][2].Empty()) {
                                if (slots[rowMax + 4][3].Empty()) {
                                    if (rowMax == 1) {
                                        return true;
                                    }
                                    else {
                                        if (slots[rowMax + 5][3].Empty()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    if (slots[rowMax + 1][4].Empty()) {
                        if (slots[rowMax + 2][3].Empty()) {
                            if (slots[rowMax + 3][2].Empty()) {
                                if (slots[rowMax + 4][1].Empty()) {
                                    if (rowMax == 1) {
                                        return true;
                                    }
                                    else {
                                        if (slots[rowMax + 5][0].Empty()) {
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
