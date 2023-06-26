package client.gui.guiMoves;

import setup.Setup;
import setup.SetupAll;

import java.util.Scanner;

public class GUISetupAll implements GUISetupInterface{
    private SetupAll setupAll;

    public SetupAll create(String nickname , String numOfPlayers ) {
        return new SetupAll(nickname);
    }

}

