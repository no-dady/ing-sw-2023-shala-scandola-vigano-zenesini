package org.gamein.model;

import org.gamein.cgc.*;

import java.util.*;

/**
 * The type Common goal card strategy.
 */
public abstract class CommonGoalCardStrategy {
    /**
     * The List common goal list.
     */
    protected static List<CommonGoalCardStrategy> listCommonGoalList = null;

    /**
     * Condition check boolean.
     *
     * @param shelf the shelf
     * @return the boolean
     */
    public abstract boolean conditionCheck (Tile[][] shelf);

    /**
     * Gets random card.
     *
     * @return the random card
     */
    public static CommonGoalCardStrategy getRandomCard() {
        if(listCommonGoalList == null) {
            listCommonGoalList = new ArrayList<CommonGoalCardStrategy>();
            listCommonGoalList.add(new SquareCheck(2));
            // 2 squares of 4 tiles of the same type
            listCommonGoalList.add(new SquareCheck(2));
            // 2 columns made of all distinct tiles
            listCommonGoalList.add(new StraightDirection(2, Bookshelf.getCols(), false, true));
            // 4 vertical strips of 4 tiles of the same type
            listCommonGoalList.add(new StraightDirection(4,4,true,true));
            // 6 vertical strips of 2 tiles of the same type
            listCommonGoalList.add(new StraightDirection(6,2,true,true));
            // 3 columns full of tiles with at least 3 tiles of the same type
            listCommonGoalList.add(new StraightDirection(4,2,true,false));
            // 2 rows full of distinct tiles
            listCommonGoalList.add(new StraightDirection(3,3,true,true));
            // 4 rows full of tiles with at least 2 tiles of the same type
            listCommonGoalList.add(new StraightDirection(2,Bookshelf.getRows(),false,false));
            // = . . . =
            // . . . . .
            // . . . . .
            // . . . . .
            // . . . . .
            // = . . . =
            listCommonGoalList.add(new SquareCheck(1));
            // . = . = .
            // . . . . .
            // = . = . =
            // . . . . .
            // = . = . =
            listCommonGoalList.add(new ShiftedCheckerboard());
            // = . =
            // . = .
            // = . =
            listCommonGoalList.add(new CrossDirection(3));
            // = . . . .
            // . = . . .
            // . . = . .
            // . . . = .
            // . . . . =
            listCommonGoalList.add(new DiagonalDirection(5,true));
            // * . . . .
            // * * . . .
            // * * * . .
            // * * * * .
            // * * * * *
            listCommonGoalList.add(new DiagonalDirection(5,false));
        }

        Random random = new Random();

        return listCommonGoalList.remove(random.nextInt(listCommonGoalList.size()));
    }

    private List<Player> players;

    /**
     * Add player.
     *
     * @param player the player
     */
    public void addPlayer(Player player){
        this.players.add(player);
    }

    /**
     * Get players list.
     *
     * @return the list
     */
    public List<Player> getPlayers(){
        return this.players;
    }
}
