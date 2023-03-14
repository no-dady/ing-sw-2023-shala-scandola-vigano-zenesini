package org.gamein.cgc;

import org.gamein.model.Tile;

public interface CommonGoalCardCondition {
    public boolean ConditionCheck (Tile[][] shelf, int numToLook, boolean isEqual, boolean isVert);
}
