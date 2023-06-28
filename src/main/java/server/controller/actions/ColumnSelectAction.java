package server.controller.actions;

import moves.MoveSelectColumn;
import server.model.Game;

public class ColumnSelectAction implements Action{
    String nickName;
    MoveSelectColumn move;

    public ColumnSelectAction(MoveSelectColumn move) {
        this.nickName = move.getNickName();
        this.move = move;
    }

    public void performAction(Game game) {
        game.getPlayers().get(game.getCurrPlayerId()).getBookshelf().setSlots(move.getSelectedColumn(), game.getSelectedTiles());
    }

    public boolean canPerformAction(Game game) {
        return move.canPerform(game);
        }
    public String getNickName() {
        return nickName;
    }
}
