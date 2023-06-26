package client.tui.tuiMoves;

import setup.SetupAll;
import setup.SetupFirst;

import java.util.Scanner;

public class TUISetupAll implements TUISetupInterface{
    private SetupAll setupAll;

    public SetupAll create(Scanner stdin, boolean firstTime) {
        String nickname;
        String numOfPlayers;
        if (firstTime)
        {
            System.out.println("[Welcome to the server!]");
            System.out.println("[Please enter your nickname]");
        } else {
            System.out.println("[The nickname was already taken, please enter another nickname]");
        }
        nickname = stdin.nextLine();
        return new SetupAll(nickname);
    }
}

