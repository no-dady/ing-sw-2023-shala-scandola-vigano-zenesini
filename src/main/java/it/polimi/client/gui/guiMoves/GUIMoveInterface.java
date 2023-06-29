package it.polimi.client.gui.guiMoves;

import it.polimi.moves.Move;
import it.polimi.server.model.Game;

public interface GUIMoveInterface {

    Move updateGUI(Game game);

    boolean canPerform(Game game);

}
