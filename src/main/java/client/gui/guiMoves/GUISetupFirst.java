package client.gui.guiMoves;

import setup.SetupFirst;

import java.util.Scanner;

public class GUISetupFirst {

    public SetupFirst create(String nickname, String numOfPlayers) {
        return new SetupFirst(nickname, numOfPlayers);
    }
}

