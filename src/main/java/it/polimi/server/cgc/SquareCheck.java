package it.polimi.server.cgc;

import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.CommonGoalCardStrategy;
import it.polimi.server.model.Tile;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The type Square check.
 *
 * @author daniel
 * @version $Id: $Id
 */
public class SquareCheck extends CommonGoalCardStrategy implements Serializable {
    /** Constant <code>className="SquareCheck"</code> */
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
     * @param name a {@link java.lang.String} object
     */
    public SquareCheck(int numToLook, String name){
        this.numToLook = numToLook;
        this.name = name;
    }
    /** {@inheritDoc} */
    @Override
    public String getClassName() {
        return className;
    }
    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
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

    /** {@inheritDoc} */
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
