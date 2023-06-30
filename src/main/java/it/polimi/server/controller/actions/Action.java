package it.polimi.server.controller.actions;

import it.polimi.server.model.Game;

/**
 * <p>Action interface.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface Action {

    /**
     * <p>performAction.</p>
     *
     * @param game a {@link it.polimi.server.model.Game} object
     */
    void performAction(Game game);

    /**
     * <p>canPerformAction.</p>
     *
     * @param game a {@link it.polimi.server.model.Game} object
     * @return a boolean
     */
    boolean canPerformAction(Game game);

    /**
     * <p>getNickName.</p>
     *
     * @return a {@link java.lang.String} object
     */
    String getNickName();
}
