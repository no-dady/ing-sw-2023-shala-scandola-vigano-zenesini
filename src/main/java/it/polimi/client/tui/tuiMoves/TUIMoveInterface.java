package it.polimi.client.tui.tuiMoves;

import it.polimi.moves.Move;
import it.polimi.server.model.Game;

import java.util.Scanner;

public interface TUIMoveInterface {

    Move updateCLI(Game game, Scanner stdin);

    boolean canPerform(Game game);

}
