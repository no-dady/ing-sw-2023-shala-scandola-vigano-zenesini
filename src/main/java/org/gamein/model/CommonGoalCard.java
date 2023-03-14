package org.gamein.model;

import org.gamein.cgc.*;

public enum CommonGoalCard {
    // 2 squares of 4 tiles of the same type
    CGC1(8, new Cgc1_8()),
    // 2 columns made of all distinct tiles
    CGC2(8, new Cgc2_3_4_6(2, Bookshelf.getCols(), false, true)),
    // 4 vertical strips of 4 tiles of the same type
    CGC3(8, new Cgc2_3_4_6(4, 4, true, true)),
    // 6 vertical strips of 2 tiles of the same type
    CGC4(8, new Cgc2_3_4_6(6, 2, true, true)),
    // 3 columns full of tiles with at least 3 tiles of the same type
    CGC5(8, new Cgc2_3_4_6(3, 3, true, true)),
    // 2 rows full of distinct tiles
    CGC6(8, new Cgc2_3_4_6(2, Bookshelf.getRows(), false, false)),
    // 4 rows full of tiles with at least 2 tiles of the same type
    CGC7(8, new Cgc2_3_4_6(4, 2, true, false)),
    // = . . . =
    // . . . . .
    // . . . . .
    // . . . . .
    // . . . . .
    // = . . . =
    CGC8(8, new Cgc1_8()),
    // . = . = .
    // . . . . .
    // = . = . =
    // . . . . .
    // = . = . =
    CGC9(8, new Cgc9()),
    // = . =
    // . = .
    // = . =
    CGC10(8, new Cgc10()),
    // = . . . .
    // . = . . .
    // . . = . .
    // . . . = .
    // . . . . =
    CGC11(8, new Cgc11_12()),
    // * . . . .
    // * * . . .
    // * * * . .
    // * * * * .
    // * * * * *
    CGC12(8, new Cgc11_12());
    private int scoreValue;
    private final CommonGoalCardCondition condition;

    CommonGoalCard(int startScore, CommonGoalCardCondition cond) {
        this.scoreValue = startScore;
        this.condition = cond;
    }

    public int getScoreValue()
    {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public CommonGoalCardCondition getCondition() {
        return condition;
    }
}
