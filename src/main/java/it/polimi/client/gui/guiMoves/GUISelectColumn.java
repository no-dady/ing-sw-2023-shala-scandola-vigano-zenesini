package it.polimi.client.gui.guiMoves;

import it.polimi.moves.Move;
import it.polimi.moves.MoveSelectColumn;
import it.polimi.server.model.Game;


/**
 * <p>GUISelectColumn class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class GUISelectColumn implements GUIMoveInterface {
    private final MoveSelectColumn move;
    private final int selectedColumn;

    /**
     * <p>Constructor for GUISelectColumn.</p>
     *
     * @param nickName a {@link java.lang.String} object
     * @param lobbyId a int
     * @param selectedColumn a int
     */
    public GUISelectColumn(String nickName, int lobbyId, int selectedColumn){
        this.move = new MoveSelectColumn(nickName, lobbyId);
        this.selectedColumn = selectedColumn;
    }
    /** {@inheritDoc} */
    @Override
    public Move updateGUI(Game game) {
        move.setSelectedColumn(selectedColumn);
        if (canPerform(game)) return move;
        else return null;
    }

    /** {@inheritDoc} */
    @Override
    public boolean canPerform(Game game) {
       return move.canPerform(game);
    }

}
