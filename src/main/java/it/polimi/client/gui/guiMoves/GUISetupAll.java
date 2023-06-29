package it.polimi.client.gui.guiMoves;

import it.polimi.setup.SetupAll;

public class GUISetupAll implements GUISetupInterface{
    private SetupAll setupAll;

    public SetupAll create(String nickname , String numOfPlayers ) {
        return new SetupAll(nickname);
    }

}

