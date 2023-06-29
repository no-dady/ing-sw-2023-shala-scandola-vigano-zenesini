package it.polimi.client.gui.guiMoves;

import it.polimi.setup.SetupFirst;

public class GUISetupFirst implements GUISetupInterface{

    public SetupFirst create(String nickname, String numOfPlayers) {
        return new SetupFirst(nickname, numOfPlayers);
    }
}

