package it.polimi.server.cgc;

import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.CommonGoalCardStrategy;
import it.polimi.server.model.Tile;
import it.polimi.server.model.TileType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * The type Square check.
 */
public class SquareCheck extends CommonGoalCardStrategy implements Serializable {
    public static final String className = "SquareCheck";
    private final String name;

    /**
     * The Num to look.
     */
    int numToLook;

    /**
     * Instantiates a new Square check.
     *
     * @param numToLook the num to look
     */
    public SquareCheck(int numToLook, String name){
        this.numToLook = numToLook;
        this.name = name;
    }
    @Override
    public String getClassName() {
        return className;
    }
    public String getName() {
        return name;
    }
    /**
     * Index equal boolean.
     *
     * @param toCheck the to check
     * @return the boolean
     */
    public boolean indexEqual(Tile[] toCheck){
        return Arrays.stream(toCheck).distinct().count() == 1;
    }

    @Override
    public boolean conditionCheck(Tile[][] shelf) {
        int count = 0;
        Tile[] corners = new Tile[4];
        corners[0] = shelf[0][0];
        corners[1] = shelf[Bookshelf.getRows()-1][Bookshelf.getCols()-1];
        corners[2] = shelf[0][Bookshelf.getCols()-1];
        corners[3] = shelf[Bookshelf.getRows()-1][0];

        if(numToLook == 1){
            return indexEqual(corners);
        }
        else {
            for (int row = 0; row < Bookshelf.getRows() - 2 && count < numToLook; row++) {
                for (int col = 0; col < Bookshelf.getCols() - 2 && count < numToLook; col++) {
                    corners[0] = shelf[row][col];
                    corners[1] = shelf[row + 1][col];
                    corners[2] = shelf[row][col + 1];
                    corners[3] = shelf[row + 1][col + 1];
                    if (indexEqual(corners)) {
                        count++;
                    }
                }
            }
            return count == numToLook;
        }
    }
}
