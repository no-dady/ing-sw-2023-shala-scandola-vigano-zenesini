package client.tui.tuiMoves;

import moves.Move;
import server.model.Game;

import java.util.Scanner;

public interface TUIInterface {

    Move updateCLI(Game game, Scanner stdin);

    boolean canPerform(Game game);

}
