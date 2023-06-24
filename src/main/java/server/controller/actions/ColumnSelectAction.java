package server.controller.actions;

import moves.MoveSelectColum;
import server.model.Game;

public class ColumnSelectAction implements Action{
    String nickName;
    MoveSelectColum move;

    public ColumnSelectAction(MoveSelectColum move) {
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
