package org.gamein.model;

// TODO: Completare enumerazione con tutte le tiles
public enum CommonGoalCard {
    COMMON_GOAL_CARD_A(8), COMMON_GOAL_CARD_B(8);
    private int scoreValue;

    private CommonGoalCard(int value1) {
        this.scoreValue = value1;
    }

    public int achievedGoal()
    {
        if (scoreValue == 0)
        {
            return scoreValue;
        }
        else
        {
            scoreValue = scoreValue - 2;
            return scoreValue + 2;
        }
    }

}
