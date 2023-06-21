package server.model;
import server.cgc.*;
import setup.ConfigsFromJson;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * The type Common goal card strategy.
 */
public abstract class CommonGoalCardStrategy implements Serializable {
    /**
     * The List common goal list.
     */
    protected static List<CommonGoalCardStrategy> listCommonGoalList = null;
    private List<Integer> points = new ArrayList<>(0);
    private String name = "";

    private int numPlayers;

    /**
     * Condition check boolean.
     *
     * @param shelf the shelf
     * @return the boolean
     */
    public abstract boolean conditionCheck (Tile[][] shelf);

    public String getName() {
        return name;
    }
    private HashMap<String, Integer> completedMap = new HashMap<>();
    public void Print() throws IOException {
        System.out.println(ConfigsFromJson.getArt("src/main/resources/json/cgcArts/" + this.getName() + ".json"));
    }

    /**
     * Gets random card.
     *
     * @return the random card
     */
    public static CommonGoalCardStrategy getRandomCard() {
        if(listCommonGoalList == null) {
            listCommonGoalList = new ArrayList<CommonGoalCardStrategy>();
            // 2 squares of 4 tiles of the same type
            listCommonGoalList.add(new SquareCheck(2, "2Squares"));
            // 2 columns made of all distinct tiles
            listCommonGoalList.add(new StraightDirection(2, Bookshelf.getCols(), false, true, "2FullDistinctCols"));
            // 4 vertical strips of 4 tiles of the same type
            listCommonGoalList.add(new StraightDirection(4,4,true,true, "4EqualColsOf4"));
            // 6 vertical strips of 2 tiles of the same type
            listCommonGoalList.add(new StraightDirection(6,2,true,true, "6EqualColsOf2"));
            // 4 rows full of tiles with at least 2 tiles of the same type
            listCommonGoalList.add(new StraightDirection(4,2,true,false, "4RowsMax3Diff"));
            // 3 columns full of tiles with at least 3 tiles of the same type
            listCommonGoalList.add(new StraightDirection(3,3,true,true, "3ColsMax3Diff"));
            // 2 rows full of distinct tiles
            listCommonGoalList.add(new StraightDirection(2,Bookshelf.getRows(),false,false, "2FullDistinctRows"));
            // = . . . =
            // . . . . .
            // . . . . .
            // . . . . .
            // . . . . .
            // = . . . =
            listCommonGoalList.add(new SquareCheck(1, "4EqualCorners"));
            // . = . = .
            // . . . . .
            // = . = . =
            // . . . . .
            // = . = . =
            listCommonGoalList.add(new ShiftedCheckerboard("ShiftedCheckerboard"));
            // = . =
            // . = .
            // = . =
            listCommonGoalList.add(new CrossDirection(3, "Cross"));
            // = . . . .
            // . = . . .
            // . . = . .
            // . . . = .
            // . . . . =
            listCommonGoalList.add(new DiagonalDirection(5,true, "Diagonal"));
            // * . . . .
            // * * . . .
            // * * * . .
            // * * * * .
            // * * * * *
            listCommonGoalList.add(new DiagonalDirection(5,false, "Stair"));
        }

        Random random = new Random();

        return listCommonGoalList.remove(random.nextInt(listCommonGoalList.size()));
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
        switch (this.numPlayers) {
            case 2 -> {
                points.add(4);
                points.add(8);
            }
            case 3 -> {
                points.add(4);
                points.add(6);
                points.add(8);
            }
            case 4 -> {
                points.add(2);
                points.add(4);
                points.add(6);
                points.add(8);
            }
        }
    }

    /**
     * Add player.
     *
     * @param player the player
     */
    public void addPlayer(Player player){
        completedMap.put(player.getUserName(), points.remove(0));
    }

    /**
     * Get players list.
     *
     * @return the list
     */
    public Integer getPlayers(Player player){
        return completedMap.remove(player.getUserName());
    }
}
