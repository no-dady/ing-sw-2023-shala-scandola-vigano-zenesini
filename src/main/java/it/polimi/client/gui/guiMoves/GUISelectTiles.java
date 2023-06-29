package it.polimi.client.gui.guiMoves;

import it.polimi.moves.Move;
import it.polimi.moves.MoveSelectTiles;
import it.polimi.server.model.Game;

public class GUISelectTiles implements GUIMoveInterface {
    private final MoveSelectTiles move;
    private final String selectedTiles;
    public GUISelectTiles(String nickName, int lobbyId, String selectedTiles){
        this.move = new MoveSelectTiles(nickName, lobbyId);
        this.selectedTiles = selectedTiles;
    }
    @Override
    public Move updateGUI(Game game) {
        move.setSelectedTiles(selectedTiles);
        if (canPerform(game)) return move;
        else return null;
    }

    @Override
    public boolean canPerform(Game game) {
        return move.canPerform(game);
    }

}
