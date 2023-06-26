package client.tui.tuiMoves;

import setup.SetupFirst;

import java.util.Scanner;

public class TUISetupFirst implements TUISetupInterface{

    public SetupFirst create(Scanner stdin, boolean firstTime) {
        String nickname;
        String numOfPlayers;
        System.out.println("[Welcome to the server!]");
        System.out.println("[Please enter your nickname]");
        nickname = stdin.nextLine();
        System.out.println("[Please enter the number of players]");
        numOfPlayers = stdin.nextLine();
        while (Integer.parseInt(numOfPlayers) < 2 || Integer.parseInt(numOfPlayers) > 4) {
            System.out.println("[Please enter a valid number of players]");
            numOfPlayers = stdin.nextLine();
        }
        return new SetupFirst(nickname, numOfPlayers);
    }
}

