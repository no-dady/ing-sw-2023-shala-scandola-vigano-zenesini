package org.gamein.model;

public enum CommonGoalCard {

    // 2 squares of 4 tiles of the same type
    CGC1(8),
    // 2 columns made of all distinct tiles
    CGC2(8),
    // 4 vertical strips of 4 tiles of the same type
    CGC3(8),
    // 6 vertical strips of 2 tiles of the same type
    CGC4(8),
    // 3 columns full of tiles with at least 3 tiles of the same type
    CGC5(8),
    // 2 rows full of distinct tiles
    CGC6(8),
    // 4 rows full of tiles with at least 2 tiles of the same type
    CGC7(8),
    // = . . . =
    // . . . . .
    // . . . . .
    // . . . . .
    // . . . . .
    // = . . . =
    CGC8(8),
    // . = . = .
    // . . . . .
    // = . = . =
    // . . . . .
    // = . = . =
    CGC9(8),
    // = . =
    // . = .
    // = . =
    CGC10(8),
    // = . . . .
    // . = . . .
    // . . = . .
    // . . . = .
    // . . . . =
    CGC11(8),
    // * . . . .
    // * * . . .
    // * * * . .
    // * * * * .
    // * * * * *
    CGC12(8);
    private int scoreValue;

    CommonGoalCard(int startScore) {
        this.scoreValue = startScore;
    }

    public int getScoreValue()
    {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }
}
