package it.polimi.server.cgc;

import it.polimi.server.model.CommonGoalCardStrategy;
import it.polimi.server.model.Tile;

import java.io.Serializable;

/**
 * The type Diagonal direction.
 *
 * @author daniel
 * @version $Id: $Id
 */
//THE COLUMN TO CALCULATE THE 12TH CARD
//DIAGONALDIRECTION
public class DiagonalDirection extends CommonGoalCardStrategy implements Serializable  {
    /** Constant <code>className="DiagonalDirection"</code> */
    public static final String className = "DiagonalDirection";
    private final int numToLook;
    private final boolean isEleven;
    private final String name;

    /**
     * Instantiates a new Diagonal direction.
     *
     * @param numToLook the num to look
     * @param isEleven  the is eleven
     * @param name a {@link java.lang.String} object
     */
    public DiagonalDirection(int numToLook, boolean isEleven, String name) {
        this.numToLook = numToLook;
        this.isEleven = isEleven;
        this.name = name;
    }
    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public String getClassName() {
        return className;
    }

    /** {@inheritDoc} */
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
                                return slots[rowMax + 4][4].getTileType().equals(typeFound);
                            }
                        }
                    }
                }
                else {
                    String typeFound = slots[rowMax][4].getTileType();
                    if (slots[rowMax + 1][3].getTileType().equals(typeFound)) {
                        if (slots[rowMax + 2][2].getTileType().equals(typeFound)) {
                            if (slots[rowMax + 3][1].getTileType().equals(typeFound)) {
                                return slots[rowMax + 4][0].getTileType().equals(typeFound);
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
                                        return slots[rowMax + 5][3].Empty();
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
                                        return slots[rowMax + 5][0].Empty();
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
