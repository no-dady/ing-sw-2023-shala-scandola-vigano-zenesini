package server.cgc;

import server.model.Bookshelf;
import server.model.CommonGoalCardStrategy;
import server.model.Tile;

import java.util.Objects;


/**
 * The type Square check.
 */
public class SquareCheck extends CommonGoalCardStrategy {

    /**
     * The Num to look.
     */
    int numToLook;

    /**
     * Instantiates a new Square check.
     *
     * @param numToLook the num to look
     */
    public SquareCheck(int numToLook){
        this.numToLook = numToLook;
    }

    /**
     * Index equal boolean.
     *
     * @param toCheck the to check
     * @return the boolean
     */
    public boolean indexEqual(Tile[] toCheck){
            return Objects.equals(toCheck[0].getTileType(), toCheck[1].getTileType()) && Objects.equals(toCheck[1].getTileType(), toCheck[2].getTileType()) && Objects.equals(toCheck[2].getTileType(), toCheck[3].getTileType());
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
