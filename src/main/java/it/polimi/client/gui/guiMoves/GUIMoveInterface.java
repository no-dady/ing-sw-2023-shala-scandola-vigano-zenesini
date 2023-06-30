package it.polimi.client.gui.guiMoves;

import it.polimi.moves.Move;
import it.polimi.server.model.Game;

/**
 * <p>GUIMoveInterface interface.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface GUIMoveInterface {

    /**
     * <p>updateGUI.</p>
     *
     * @param game a {@link it.polimi.server.model.Game} object
     * @return a {@link it.polimi.moves.Move} object
     */
    Move updateGUI(Game game);

    /**
     * <p>canPerform.</p>
     *
     * @param game a {@link it.polimi.server.model.Game} object
     * @return a boolean
     */
    boolean canPerform(Game game);

}
