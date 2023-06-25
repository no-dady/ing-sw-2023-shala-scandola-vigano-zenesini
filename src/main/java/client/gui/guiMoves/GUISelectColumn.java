package client.gui.guiMoves;

import moves.Move;
import moves.MoveSelectColum;
import server.model.Game;


public class GUISelectColumn implements GUIInterface {
    private final MoveSelectColum move;
    private final int selectedColumn;

    public GUISelectColumn(String nickName, int selectedColumn){
        this.move = new MoveSelectColum(nickName);
        this.selectedColumn = selectedColumn;
    }
    @Override
    public Move updateGUI(Game game) {
        move.setSelectedColumn(selectedColumn);
        return move;
    }

    @Override
    public boolean canPerform(Game game) {
       return move.canPerform(game);
    }

}
