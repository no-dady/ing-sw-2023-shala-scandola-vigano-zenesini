package it.polimi.server.cgc;

import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.CommonGoalCardStrategy;
import it.polimi.server.model.Tile;

import java.io.Serializable;
import java.util.*;

/**
 * <p>MaxNTypes class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class MaxNTypes extends CommonGoalCardStrategy implements Serializable {
    /** Constant <code>className="MaxNTypes"</code> */
    public static final String className = "MaxNTypes";
    private final boolean isVertical;
    private final int timesToCheck;

    private final int maxDistincts;
    private final String name;


    /**
     * <p>Constructor for MaxNTypes.</p>
     *
     * @param isVertical a boolean
     * @param maxDistincts a int
     * @param timesToCheck a int
     * @param name a {@link java.lang.String} object
     */
    public MaxNTypes(boolean isVertical, int maxDistincts, int timesToCheck, String name) {
        this.isVertical = isVertical;
        this.timesToCheck = timesToCheck;
        this.maxDistincts = maxDistincts;
        this.name = name;
    }
    /** {@inheritDoc} */
    @Override
    public boolean conditionCheck(Tile[][] shelf) {
        return isVertical ? checkColumns(shelf) :  checkRows(shelf);
    }

    private boolean checkColumns(Tile[][] shelf) {
        int count = 0;
        Set<String> foundTypes = new HashSet<>();
        for(int i = 0; i < Bookshelf.getCols() && count < timesToCheck; i++) {
            for(int j = 0; j < Bookshelf.getRows(); j++) {
                foundTypes.add(shelf[j][i].getTileType());
            }

            if(!foundTypes.contains("EMPTY") && foundTypes.size() <= maxDistincts)
                count++;

            foundTypes.clear();
        }

        return count == timesToCheck;
    }
    private boolean checkRows(Tile[][] shelf) {
        int count = 0;
        Set<String> foundTypes = new HashSet<>();
        for(int i = 0; i < Bookshelf.getRows() && count < timesToCheck; i++) {
            for(int j = 0; j < Bookshelf.getCols(); j++) {
                foundTypes.add(shelf[i][j].getTileType());
            }

            if(!foundTypes.contains("EMPTY") && foundTypes.size() <= maxDistincts)
                count++;

            foundTypes.clear();
        }
        return count == timesToCheck;
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

}
