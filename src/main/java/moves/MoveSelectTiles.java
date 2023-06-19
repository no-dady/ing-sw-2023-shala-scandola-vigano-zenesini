package moves;

import server.model.Game;

public class MoveSelectTiles extends Move {
    private String selectedTiles;
    private final String name = "SelectTiles";

    public MoveSelectTiles(String nickName) {
        super(nickName);
    }

    @Override
    public String getName() {
        return name;
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
