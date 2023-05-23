package moves;

import server.model.Game;
import server.model.Tile;

public class MoveSelectTiles extends Move {
    String selectedTiles;

    public MoveSelectTiles(String nickName) {
        super(nickName);
    }

    @Override
    public boolean canPerform(Game game) {
        return true;
    }

    public void setSelectedTiles(String selectedTiles) {
        this.selectedTiles = selectedTiles;
    }
    public String getSelectedTiles(){
        return this.selectedTiles;
    }
}
