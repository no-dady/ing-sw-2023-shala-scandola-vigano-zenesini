package client.gui.guiMoves;

import moves.Move;
import moves.MoveSelectColum;
import server.model.Game;


public class GUISelectColumn implements GUIMoveInterface {
    private final MoveSelectColum move;
    private final int selectedColumn;

    public GUISelectColumn(String nickName, int lobbyId, int selectedColumn){
        this.move = new MoveSelectColum(nickName, lobbyId);
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
