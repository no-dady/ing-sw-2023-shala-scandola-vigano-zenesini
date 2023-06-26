package client.gui.guiMoves;

import moves.Move;
import server.model.Game;

public interface GUIMoveInterface {

    Move updateGUI(Game game);

    boolean canPerform(Game game);

}
