package client.tui.tuiMoves;

import setup.SetupAll;
import setup.SetupFirst;

import java.util.Scanner;

public class TUISetupAll {
    private SetupAll setupAll;

    public SetupAll create(Scanner stdin) {
        String nickname;
        String numOfPlayers;
        System.out.println("[Welcome to the server!]");
        System.out.println("[Please enter your nickname]");
        nickname = stdin.nextLine();
        return new SetupAll(nickname);
    }
}

