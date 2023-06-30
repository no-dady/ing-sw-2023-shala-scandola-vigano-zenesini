package it.polimi.server.cgc;

import it.polimi.server.model.CommonGoalCardStrategy;
import it.polimi.server.model.Tile;

import java.io.Serializable;

/**
 * The type Shifted checkerboard.
 *
 * @author daniel
 * @version $Id: $Id
 */
public class ShiftedCheckerboard extends CommonGoalCardStrategy implements Serializable {
    /** Constant <code>className="ShiftedCheckerboard"</code> */
    public static final String className = "ShiftedCheckerboard";
    private final String name;
    /**
     * <p>Constructor for ShiftedCheckerboard.</p>
     *
     * @param name a {@link java.lang.String} object
     */
    public ShiftedCheckerboard(String name){
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
    public boolean conditionCheck(Tile[][] shelf) {

        for (int k = 0; k < 2; k++){
            int p=0;
            if (shelf[k][p].equals(shelf[k][p + 2]) && shelf[k][p + 2].equals(shelf[k][p + 4]) && shelf[k][p + 4].equals(shelf[k + 2][p]) && shelf[k + 2][p].equals(shelf[k + 2][p + 2]) && shelf[k + 2][p + 2].equals(shelf[k + 2][p + 4]) && shelf[k + 2][p + 4].equals(shelf[k + 4][p + 1]) && shelf[k + 4][p + 1].equals(shelf[k + 4][p + 3]))
            {
                return true;
            }
        }
        return false;
    }
    /** {@inheritDoc} */
    @Override
    public String getClassName() {
        return className;
    }
}
