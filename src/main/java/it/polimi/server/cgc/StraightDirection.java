package it.polimi.server.cgc;

import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.CommonGoalCardStrategy;
import it.polimi.server.model.Tile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Straight direction.
 */
public class StraightDirection extends CommonGoalCardStrategy implements Serializable {
    private final int numToLook;
    private final int timesToLook;
    private final boolean isEqual;
    private final boolean isVert;
    private final String name;
    public static final String className = "StraightDirection";

    /**
     * Instantiates a new Straight direction.
     *
     * @param timesToLook the times to look
     * @param numToLook   the num to look
     * @param isEqual     the is equal
     * @param isVert      the is vert
     */
    public StraightDirection(int timesToLook, int numToLook, boolean isEqual, boolean isVert, String name) {
        this.timesToLook = timesToLook;
        this.numToLook = numToLook;
        this.isEqual = isEqual;
        this.isVert = isVert;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    // 4 vertical strips of 4 tiles of the same type
    @Override
    public boolean conditionCheck(Tile[][] slots) {
        return isVert ? checkColumns(slots) : checkRows(slots);
    }

    private boolean checkColumns(Tile[][] slots) {
        int count = 0, checked = 0;

        for(int i = 0; i < Bookshelf.getCols() && checked < timesToLook; i++) {
            var tile = slots[0][i];
            count = 0;
            for(int j = 0; j < Bookshelf.getRows() && !slots[j][i].Empty() && checked < timesToLook; ++j) {
                if((slots[j][i].equals(tile)) == isEqual) {
                    count++;
                } else {
                    tile = slots[j][i];
                    count = 1;
                }

                if(count == numToLook) {
                    checked++;
                    count = 1;
                }
            }

        }

        return (checked == timesToLook);
    }

    private boolean checkRows(Tile[][] slots) {
        int count = 0, checked = 0;

        if(!isEqual) {
           for(int i = 0; i < Bookshelf.getRows() && checked < timesToLook; i++) {
               Set<String> tileTypes = new HashSet<>();
               for(int j = 0; j < Bookshelf.getCols() && !slots[i][j].Empty(); j++) {
                   tileTypes.add(slots[i][j].getTileType());
               }
               if(Bookshelf.getCols() == tileTypes.size()) {
                   checked++;
               }
           }
        }
        else
        {
            for(int i = 0; i < Bookshelf.getRows() && checked < timesToLook; i++) {
                var tile = slots[i][0];
                count = 0;
                for(int j = 0; j < Bookshelf.getCols() && checked < timesToLook && !slots[i][j].Empty() ; ++j) {
                    if((slots[i][j].equals(tile)) == isEqual) {
                        count++;
                    } else {
                        tile = slots[i][j];
                        count = 1;
                    }

                    if(count == numToLook) {
                        checked++;
                        count = 1;
                    }
                }
            }

        }

        return (checked == timesToLook);
    }
    @Override
    public String getClassName() {
        return className;
    }
}
