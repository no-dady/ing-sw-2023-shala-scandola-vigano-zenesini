package server.controller.actions;

import moves.MoveSelectTiles;
import server.model.Game;
import server.model.Tile;

import java.util.List;
import java.util.Scanner;

public class TileSelectAction extends MoveSelectTiles implements Action{
    List<Tile> selectedTiles;
    public TileSelectAction(String nickName) {
        super(nickName);
    }

    @Override
    public void performAction(Game game) {
        if (canPerformAction(game)) {
            game.setSelectedTiles(selectedTiles);
        }
    }

    @Override
    public boolean canPerformAction(Game game) {
        String[] selected = super.getSelectedTiles().split(" ");
        for (String s : selected) {
            if (!game.getBoard().getTile(s.charAt(1) - 'a', s.charAt(1) - '1').isPickable()) {
                return false;
            } else {
                selectedTiles.add(game.getBoard().getTile(s.charAt(1) - 'a', s.charAt(1) - '1'));
            }
        }
        return true;
    }

    @Override
    public String getNickName() {
        return "";
    }

}
