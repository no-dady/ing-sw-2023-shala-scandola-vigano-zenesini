package it.polimi.client.tui.tuiMoves;

import it.polimi.setup.Setup;

import java.util.Scanner;

public interface TUISetupInterface {
    public Setup create(Scanner scanner, boolean firstTime);

}
