package org.gamein.model;

import org.gamein.cgc.*;

import java.util.List;

public enum CommonGoalCard {
    // 2 squares of 4 tiles of the same type
    CGC1(new Cgc1_8(2)),
    // 2 columns made of all distinct tiles
    TWO_DISTINCT_COLUMNS(new StraightDirection(2, Bookshelf.getCols(), false, true)),
    // 4 vertical strips of 4 tiles of the same type
    FOUR_VSTRIPS_4EQUAL(new StraightDirection(4, 4, true, true)),
    // 6 vertical strips of 2 tiles of the same type
    SIX_VSTRIPS_2EQUAL(new StraightDirection(6, 2, true, true)),
    // 3 columns full of tiles with at least 3 tiles of the same type
    THREE_COL_MIN3EQUAL(new StraightDirection(3, 3, true, true)),
    // 2 rows full of distinct tiles
    TWO_DISTINCT_ROWS(new StraightDirection(2, Bookshelf.getRows(), false, false)),
    // 4 rows full of tiles with at least 2 tiles of the same type
    FOUR_ROWS_MIN2EQUAL(new StraightDirection(4, 2, true, false)),
    // = . . . =
    // . . . . .
    // . . . . .
    // . . . . .
    // . . . . .
    // = . . . =
    CGC8(new Cgc1_8(1)),
    // . = . = .
    // . . . . .
    // = . = . =
    // . . . . .
    // = . = . =
    CGC9(new Cgc9()),
    // = . =
    // . = .
    // = . =
    CGC10(new CrossDirection(3)),
    // = . . . .
    // . = . . .
    // . . = . .
    // . . . = .
    // . . . . =
    CGC11(new DiagonalDirection(5, true)),
    // * . . . .
    // * * . . .
    // * * * . .
    // * * * * .
    // * * * * *
    CGC12(new DiagonalDirection(5, false));
    private int scoreValue;

    private List<Player> players;
    private final CommonGoalCardCondition condition;

    CommonGoalCard(CommonGoalCardCondition cond) {
        this.condition = cond;
    }

    public int getScoreValue()
    {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

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
