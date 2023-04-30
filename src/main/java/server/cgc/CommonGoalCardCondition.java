package server.cgc;

import server.model.Tile;

public interface CommonGoalCardCondition {
    boolean conditionCheck (Tile[][] shelf);
}
