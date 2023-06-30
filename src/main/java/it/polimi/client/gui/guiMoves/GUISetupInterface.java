package it.polimi.client.gui.guiMoves;

import it.polimi.setup.Setup;

/**
 * <p>GUISetupInterface interface.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface GUISetupInterface {
    /**
     * <p>create.</p>
     *
     * @param nickname a {@link java.lang.String} object
     * @param numOfPlayers a {@link java.lang.String} object
     * @return a {@link it.polimi.setup.Setup} object
     */
    Setup create(String nickname, String numOfPlayers);

}
