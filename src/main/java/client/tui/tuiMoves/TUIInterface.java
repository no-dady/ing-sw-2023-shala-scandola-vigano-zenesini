package client.tui.tuiMoves;

import moves.Move;
import moves.MoveSelectTiles;
import server.model.Game;

import java.util.Scanner;

public interface TUIInterface {

    Move updateCLI(Game game, Scanner stdin);

    boolean canPerform(Game game);
    String getName();


}
