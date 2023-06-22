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
        while (!canPerformAction(game))
            game.getPlayers().get(game.getCurrPlayerId()).getBookshelf().setSlots(move.getSelectedColumn(), game.getSelectedTiles());
        game.setSelectedTiles(null);
    }

    public boolean canPerformAction(Game game) {
        int selectedY = move.getSelectedColumn();
        return game.getPlayers().get(game.getCurrPlayerId()).getBookshelf().getEmptyTilesColumn(selectedY) >= game.getSelectedTiles().size();
    }
    public String getNickName() {
        return nickName;
    }

}
