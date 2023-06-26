package client.gui.guiMoves;

import setup.Setup;
import setup.SetupFirst;

public class GUISetupFirst implements GUISetupInterface{

    public SetupFirst create(String nickname, String numOfPlayers) {
        return new SetupFirst(nickname, numOfPlayers);
    }
}

