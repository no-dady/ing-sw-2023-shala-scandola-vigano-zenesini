package client.gui.guiMoves;

import moves.Move;
import server.model.Game;

import java.util.Scanner;

public interface GUIInterface {

    Move updateGUI(Game game);

    boolean canPerform(Game game);

}
