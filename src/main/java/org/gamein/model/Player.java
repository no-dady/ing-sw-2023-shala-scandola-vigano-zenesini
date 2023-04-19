package org.gamein.model;

import java.io.Serializable;

public class Player implements Serializable {
    private final int userId;
    private String username;
    private int score;
    private final Bookshelf personalBookshelf;
    private final PersonalGoalCard personalGoalCard;

    public Player(int userId, String username, Bookshelf personalBookshelf, PersonalGoalCard personalGoalCard) {
        this.userId = userId;
        this.username = username;
        this.personalBookshelf = personalBookshelf;
        this.personalGoalCard = personalGoalCard;
    }

    /*
     * @return player's userid
     */
    public int getUserId()
    {
        return this.userId;
    }

    /*
     * @return player's username
     */
    public String getUserName()
    {
        return this.username;
    }

    /*
     * @return istance of the player's shelf
     */
    public Bookshelf getBookshelf()
    {
        return this.personalBookshelf;
    }

    /*
     * @return istance of the player's personalGoalCard
     */
    public PersonalGoalCard getPersonalGoalCard()
    {
        return this.personalGoalCard;
    }

    /*
     * @param score Sets the player score
     */
    public void setScore(int score)
    {
        this.score = score;
    }

    /*
     * @return player score
     */
    public int getScore()
    {
        return this.score;
    }

    /*
     * @NotNull
     * @param username not already taken
     */
    public void setUserName(String username) { this.username = username; }

    public boolean isWinner() {
        return personalBookshelf.getNumTiles() == (Bookshelf.getRows() * Bookshelf.getCols());
    }
}
