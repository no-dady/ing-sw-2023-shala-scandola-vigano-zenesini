package it.polimi.server.cgc;

import it.polimi.server.model.CommonGoalCardStrategy;
import it.polimi.server.model.Tile;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Shifted checkerboard.
 */
public class ShiftedCheckerboard extends CommonGoalCardStrategy implements Serializable {
    public static final String className = "ShiftedCheckerboard";
    private final String name;
    public ShiftedCheckerboard(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public boolean conditionCheck(Tile[][] shelf) {

        for (int k = 0; k < 2; k++){
            int p=0;
            if (Objects.equals(shelf[k][p].getTileType(), shelf[k][p + 2].getTileType()) && Objects.equals(shelf[k][p + 2].getTileType(), shelf[k][p + 4].getTileType()) && Objects.equals(shelf[k][p + 4].getTileType(), shelf[k + 2][p].getTileType()) && Objects.equals(shelf[k + 2][p].getTileType(), shelf[k + 2][p + 2].getTileType()) && Objects.equals(shelf[k + 2][p + 2].getTileType(), shelf[k + 2][p + 4].getTileType()) && Objects.equals(shelf[k + 2][p + 4].getTileType(), shelf[k + 4][p + 1].getTileType()) && Objects.equals(shelf[k + 4][p + 1].getTileType(), shelf[k + 4][p + 3].getTileType()))
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public String getClassName() {
        return className;
    }
}
