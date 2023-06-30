package it.polimi.client.tui.tuiMoves;

import it.polimi.setup.SetupAll;

import java.util.Scanner;

/**
 * <p>TUISetupAll class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class TUISetupAll implements TUISetupInterface{
    private SetupAll setupAll;

    /** {@inheritDoc} */
    public SetupAll create(Scanner stdin, boolean firstTime) {
        String nickname;
        if (firstTime)
        {
            System.out.println("[Welcome to the Server!]");
            System.out.println("[Please enter your nickname]");
        } else {
            System.out.println("[The nickname was already taken, please enter another nickname]");
        }
        nickname = stdin.nextLine();
        return new SetupAll(nickname);
    }
}

