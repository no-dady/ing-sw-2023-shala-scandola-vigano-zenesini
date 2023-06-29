package it.polimi.client.gui.guiMoves;

import it.polimi.moves.Move;
import it.polimi.moves.MoveSelectColumn;
import it.polimi.server.model.Game;


public class GUISelectColumn implements GUIMoveInterface {
    private final MoveSelectColumn move;
    private final int selectedColumn;

    public GUISelectColumn(String nickName, int lobbyId, int selectedColumn){
        this.move = new MoveSelectColumn(nickName, lobbyId);
        this.selectedColumn = selectedColumn;
    }
    @Override
    public Move updateGUI(Game game) {
        move.setSelectedColumn(selectedColumn);
        if (canPerform(game)) return move;
        else return null;
    }

    @Override
    public boolean canPerform(Game game) {
       return move.canPerform(game);
    }

}
