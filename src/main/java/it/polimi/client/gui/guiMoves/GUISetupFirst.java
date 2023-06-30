package it.polimi.client.gui.guiMoves;

import it.polimi.setup.SetupFirst;

/**
 * <p>GUISetupFirst class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class GUISetupFirst implements GUISetupInterface{

    /** {@inheritDoc} */
    public SetupFirst create(String nickname, String numOfPlayers) {
        return new SetupFirst(nickname, numOfPlayers);
    }
}

