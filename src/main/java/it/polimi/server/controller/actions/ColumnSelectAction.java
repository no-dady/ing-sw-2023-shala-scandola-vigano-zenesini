package it.polimi.server.controller.actions;

import it.polimi.moves.MoveSelectColumn;
import it.polimi.server.model.Game;

/**
 * <p>ColumnSelectAction class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class ColumnSelectAction implements Action{
    String nickName;
    MoveSelectColumn move;

    /**
     * <p>Constructor for ColumnSelectAction.</p>
     *
     * @param move a {@link it.polimi.moves.MoveSelectColumn} object
     */
    public ColumnSelectAction(MoveSelectColumn move) {
        this.nickName = move.getNickName();
        this.move = move;
    }

    /** {@inheritDoc} */
    public void performAction(Game game) {
        game.getPlayers().get(game.getCurrPlayerId()).getBookshelf().setSlots(move.getSelectedColumn(), game.getSelectedTiles());
    }

    /** {@inheritDoc} */
    public boolean canPerformAction(Game game) {
        return move.canPerform(game);
        }
    /**
     * <p>Getter for the field <code>nickName</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getNickName() {
        return nickName;
    }
}
