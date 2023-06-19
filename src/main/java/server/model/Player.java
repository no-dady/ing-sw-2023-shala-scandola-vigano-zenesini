package server.model;

import java.io.Serializable;

/**
 * The type Player.
 */
public class Player implements Serializable {
    private final int userId;
    private final String username;
    private int score;
    private Bookshelf personalBookshelf;
    private final PersonalGoalCard personalGoalCard;

    /**
     * Instantiates a new Player.
     *
     * @param userId            the user id
     * @param username          the username
     * @param personalBookshelf the personal bookshelf
     * @param personalGoalCard  the personal goal card
     */
    public Player(int userId, String username, Bookshelf personalBookshelf, PersonalGoalCard personalGoalCard) {
        this.userId = userId;
        this.username = username;
        this.personalBookshelf = personalBookshelf;
        this.personalGoalCard = personalGoalCard;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId()
    {
        return this.userId;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName()
    {
        return this.username;
    }

    /**
     * Gets bookshelf.
     *
     * @return the bookshelf
     */
    public Bookshelf getBookshelf()
    {
        return this.personalBookshelf;
    }
    public void setPersonalBookshelf(Bookshelf personalBookshelf) {
        this.personalBookshelf = personalBookshelf;
    }

    /**
     * Gets personal goal card.
     *
     * @return the personal goal card
     */
    public PersonalGoalCard getPersonalGoalCard()
    {
        return this.personalGoalCard;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore()
    {
        return this.score;
    }

    /**
     * Sets user name.
     *
     * @param username the username
     */
    public void setUserName(String username) { this.username = username; }

    /**
     * Is winner boolean.
     *
     * @return the boolean
     */
    public boolean isWinner() {
        return personalBookshelf.getNumTiles() == (Bookshelf.getRows() * Bookshelf.getCols());
    }
}
