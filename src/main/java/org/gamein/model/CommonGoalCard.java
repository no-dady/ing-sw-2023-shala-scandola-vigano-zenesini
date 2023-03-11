package org.gamein.model;

public enum CommonGoalCard {
    COMMON_GOAL_CARD_A(8), COMMON_GOAL_CARD_B(8);
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
