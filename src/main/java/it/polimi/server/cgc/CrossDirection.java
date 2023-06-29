package it.polimi.server.cgc;

import it.polimi.server.model.Tile;
import it.polimi.server.model.CommonGoalCardStrategy;

import java.io.Serializable;

/**
 * The type Cross direction.
 */
//CROSSDIRECTION
public class CrossDirection extends CommonGoalCardStrategy implements Serializable {
    public static final String className = "CrossDirection";
    private final int numToLook;
    private final String name;

    /**
     * Instantiates a new Cross direction.
     *
     * @param numToLook the num to look
     */
    public CrossDirection(int numToLook, String name) {
        this.numToLook = numToLook;
        this.name = name;
    }

    public String getName() {
        return name;
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
                if (!shelf[row][column].Empty())
                {
                    String typeFound = shelf[row][column].getTileType();

                    if (shelf[row + 2][column].getTileType().equals(typeFound) && shelf[row + 1][column + 1].getTileType().equals(typeFound) && shelf[row][column + 2].getTileType().equals(typeFound) && shelf[row + 2][column + 2].getTileType().equals(typeFound))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public String getClassName() {
        return className;
    }
}
