package server.cgc;

import server.model.Tile;

public class ShiftedCheckerboard implements CommonGoalCardCondition{

    @Override
    public boolean conditionCheck(Tile[][] shelf) {
        int p=0;
        for (int k = 0; k < 2; k++){
            if (!shelf[k][p].Empty() && !shelf[k][p+2].Empty() && !shelf[k][p+4].Empty() && !shelf[k+2][p].Empty() && !shelf[k+2][p+2].Empty() && !shelf[k+2][p+4].Empty() && !shelf[k+4][p+3].Empty() && !shelf[k+4][p+1].Empty() && shelf[k][p].getTileType().equals(shelf[k][p + 2].getTileType()) && shelf[k][p + 2].getTileType().equals(shelf[k][p + 4].getTileType()) && shelf[k][p + 4].getTileType().equals(shelf[k + 2][p].getTileType()) && shelf[k + 2][p].getTileType().equals(shelf[k + 2][p + 2].getTileType()) && shelf[k + 2][p + 2].getTileType().equals(shelf[k + 2][p + 4].getTileType()) && shelf[k + 2][p + 4].getTileType().equals(shelf[k + 4][p + 1].getTileType()) && shelf[k + 4][p + 1].getTileType().equals(shelf[k + 4][p + 3].getTileType()))
            {
                return true;
            }
        }
        return false;
    }
}
