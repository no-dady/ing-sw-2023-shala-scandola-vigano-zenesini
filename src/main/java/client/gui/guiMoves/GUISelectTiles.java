package client.gui.guiMoves;

import moves.Move;
import moves.MoveSelectTiles;
import server.model.Game;

import java.util.Scanner;

public class GUISelectTiles implements GUIInterface {
    private final MoveSelectTiles move;
    private final String selectedTiles;
    public GUISelectTiles(String nickName, String selectedTiles){
        this.move = new MoveSelectTiles(nickName);
        this.selectedTiles = selectedTiles;
    }
    @Override
    public Move updateGUI(Game game) {
        move.setSelectedTiles(selectedTiles);
        if (canPerform(game)) return move;
        else return null;
    }

    @Override
    public boolean canPerform(Game game) {
        return move.canPerform(game);
    }

}
