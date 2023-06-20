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
        if (canPerformAction(game)) {
            game.setSelectedTiles(selectedTiles);
        }
    }

    public boolean canPerformAction(Game game) {
        String[] selected = move.getSelectedTiles().split(" ");
        for (String s : selected) {
            if (!game.getBoard().getTile(s.charAt(1) - 'a', s.charAt(1) - '1').isPickable()) {
                return false;
            } else {
                selectedTiles.add(game.getBoard().getTile(s.charAt(1) - 'a', s.charAt(1) - '1'));
            }
        }
        return true;
    }

    public String getNickName() {
        return nickName;
    }

}
