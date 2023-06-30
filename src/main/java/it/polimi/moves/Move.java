package it.polimi.moves;


import it.polimi.server.model.Game;

/**
 * <p>Move interface.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface Move {

    /**
     * <p>getNickName.</p>
     *
     * @return a {@link java.lang.String} object
     */
    String getNickName();

    /**
     * <p>getLobbyId.</p>
     *
     * @return a int
     */
    int getLobbyId();
    /**
     * <p>canPerform.</p>
     *
     * @param game a {@link it.polimi.server.model.Game} object
     * @return a boolean
     */
    boolean canPerform(Game game);

    /**
     * <p>getClassName.</p>
     *
     * @return a {@link java.lang.String} object
     */
    String getClassName();
}
