package org.gamein.model;

import org.javatuples.Pair;

import java.awt.print.Book;

public class Player {
    private final int userId;
    private String userName;
    private int score;
    private final Bookshelf personalBookshelf;
    private final PersonalGoalCard personalGoalCard;
    private Pair <Boolean, Boolean> achievedCommon;

    public Player(int userId, String userName, Bookshelf personalBookshelf, PersonalGoalCard personalGoalCard) {
        this.userId = userId;
        this.userName = userName;
        this.personalBookshelf = personalBookshelf;
        this.personalGoalCard = personalGoalCard;
        this.achievedCommon = new Pair<>(Boolean.FALSE, Boolean.FALSE);
    }

    public Pair <Boolean,Boolean> getAchieved()
    {
        return this.achievedCommon;
    }

    public int getScore()
    {
        return this.score;
    }

    public int getUserId()
    {
        return this.userId;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public Bookshelf getBookshelf()
    {
        return this.personalBookshelf;
    }

    public PersonalGoalCard getPersonalGoalCard()
    {
        return this.personalGoalCard;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void setAchievedCommon(Pair <Boolean,Boolean> achievedInTurn)
    {
        this.achievedCommon = achievedInTurn;
    }

    public void setUserName(String userName) { this.userName = userName; }
}
