package client.gui.guiMoves;

import setup.SetupAll;

import java.util.Scanner;

public class GUISetupAll {
    private SetupAll setupAll;

    public SetupAll create(String nickname  ) {
        return new SetupAll(nickname);
    }
}

