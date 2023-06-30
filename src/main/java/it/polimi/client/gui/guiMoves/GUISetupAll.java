package it.polimi.client.gui.guiMoves;

import it.polimi.setup.SetupAll;

/**
 * <p>GUISetupAll class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class GUISetupAll implements GUISetupInterface{
    private SetupAll setupAll;

    /** {@inheritDoc} */
    public SetupAll create(String nickname , String numOfPlayers ) {
        return new SetupAll(nickname);
    }

}

