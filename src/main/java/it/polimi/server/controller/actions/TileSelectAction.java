package it.polimi.server.controller.actions;

import it.polimi.moves.MoveSelectTiles;
import it.polimi.server.model.Game;
import it.polimi.server.model.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileSelectAction implements Action{
    List<Tile> selectedTiles = new ArrayList<>();
    MoveSelectTiles move;
    String nickName;
    public TileSelectAction(MoveSelectTiles move) {
        this.nickName = move.getNickName();
        this.move = move;
    }

    public void performAction(Game game) {
        for (String s : move.getSelectedTiles().split(" ")) {
            selectedTiles.add(game.getBoard().getTile(s.charAt(0) - 'A', s.charAt(1) - '1'));
            game.getBoard().removeTile(s.charAt(0) - 'A', s.charAt(1) - '1');
        }
        game.setSelectedTiles(selectedTiles);
    }

    public boolean canPerformAction(Game game) {
        return move.canPerform(game);
    }

    public String getNickName() {
        return nickName;
    }

}
