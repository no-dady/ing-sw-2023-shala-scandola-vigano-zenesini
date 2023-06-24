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
        if (!((selected[1].charAt(1)-'A' == selected[2].charAt(0)-'A') && (selected[2].charAt(0)-'A'  == selected[0].charAt(0)-'A')) || (selected[1].charAt(1)-'1' == selected[2].charAt(1)-'1'&& selected[2].charAt(1)-'1'  == selected[0].charAt(1)-'1' )){
            return false;
        }
        for (String s : selected) {
            if (!game.getBoard().getTile(s.charAt(1) - 'A', s.charAt(1) - '1').isPickable()) {
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
