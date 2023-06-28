package server.cgc;

import server.model.Bookshelf;
import server.model.CommonGoalCardStrategy;
import server.model.Tile;
import server.model.TileType;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class MaxNTypes extends CommonGoalCardStrategy implements Serializable {
    public static final String className = "MaxNTypes";
    private boolean isVertical;
    private int timesToCheck;

    private int maxDistincts;


    public MaxNTypes(boolean isVertical, int maxDistincts, int timesToCheck, String name) {
        this.isVertical = isVertical;
        this.timesToCheck = timesToCheck;
        this.maxDistincts = maxDistincts;
        this.name = name;
    }
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

    @Override
    public String getClassName() {
        return className;
    }
    public String getName() {
        return name;
    }

}
