package server.controller.actions;

import moves.MoveSelectTiles;
import server.model.Game;
import server.model.Tile;

import java.util.List;

public class TileSelectAction implements Action{
    List<Tile> selectedTiles;
    MoveSelectTiles move;
    String nickName;
    public TileSelectAction(MoveSelectTiles move) {
        this.nickName = move.getNickName();
        this.move = move;
    }

    public void performAction(Game game) {
        game.setSelectedTiles(selectedTiles);
    }

    public boolean canPerformAction(Game game) {
        return move.canPerform(game);
    }

    public String getNickName() {
        return nickName;
    }

}
