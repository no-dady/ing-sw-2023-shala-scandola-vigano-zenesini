package server.model;

import org.gamein.cgc.*;
import server.cgc.*;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class CommonGoalCard {
    protected static Map<String, CommonGoalCardCondition> commonGoalEnum;
    private List<CommonGoalCardCondition> listCond;
    private CommonGoalCardCondition condition;
    public CommonGoalCard() {
        commonGoalEnum = new TreeMap<String, CommonGoalCardCondition>();
        // 2 squares of 4 tiles of the same type
        commonGoalEnum.put("TWO_4EQ_TILES_SQUARE", new SquareCheck(2));
        // 2 columns made of all distinct tiles
        commonGoalEnum.put("TWO_DISTINCT_COLUMNS", new StraightDirection(2, Bookshelf.getCols(), false, true));
        // 4 vertical strips of 4 tiles of the same type
        commonGoalEnum.put("FOUR_VSTRIPS_4EQUAL", new StraightDirection(4,4,true,true));
        // 6 vertical strips of 2 tiles of the same type
        commonGoalEnum.put("SIX_VSTRIPS_2EQUAL", new StraightDirection(6,2,true,true));
        // 3 columns full of tiles with at least 3 tiles of the same type
        commonGoalEnum.put("THREE_COL_MIN3EQUAL", new StraightDirection(4,2,true,false));
        // 2 rows full of distinct tiles
        commonGoalEnum.put("TWO_DISTINCT_ROWS", new StraightDirection(3,3,true,true));
        // 4 rows full of tiles with at least 2 tiles of the same type
        commonGoalEnum.put("FOUR_ROWS_MIN2EQUAL", new StraightDirection(2,Bookshelf.getRows(),false,false));
        // = . . . =
        // . . . . .
        // . . . . .
        // . . . . .
        // . . . . .
        // = . . . =
        commonGoalEnum.put("SHELF_CORNERS_EQ", new SquareCheck(1));
        // . = . = .
        // . . . . .
        // = . = . =
        // . . . . .
        // = . = . =
        commonGoalEnum.put("SHIFTED_CHECKERBOARD_EQ", new ShiftedCheckerboard());
        // = . =
        // . = .
        // = . =
        commonGoalEnum.put("CROSS_EQ", new CrossDirection(3));
        // = . . . .
        // . = . . .
        // . . = . .
        // . . . = .
        // . . . . =
        commonGoalEnum.put("SEC_DIAGONAL_EQ", new DiagonalDirection(5,true));
        // * . . . .
        // * * . . .
        // * * * . .
        // * * * * .
        // * * * * *
        commonGoalEnum.put("LOW_TRI_MATRIX", new DiagonalDirection(5,false));
    }

    public CommonGoalCard(CommonGoalCardCondition condition) {
        this.condition = condition;
    }


    public CommonGoalCard getRandGoalCard() {
        if(listCond == null) listCond = commonGoalEnum.values().stream().toList();
        Random rand = new Random(listCond.size());
        return new CommonGoalCard(listCond.remove(rand.nextInt()));
    }

    public static Map<String, CommonGoalCardCondition> getCgcMap() {
        return commonGoalEnum;
    }

    private List<Player> players;
    public void addPlayer(Player player){
        this.players.add(player);
    }
    public List<Player> getPlayers(){
        return this.players;
    }
    public CommonGoalCardCondition getCondition() {
        return condition;
    }
}
