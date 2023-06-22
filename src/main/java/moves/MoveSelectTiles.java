package moves;

import server.model.Game;

public class MoveSelectTiles extends Move {
    private String selectedTiles;
    private final String classname = "MoveSelectTiles";

    public MoveSelectTiles(String nickName) {
        super(nickName);
    }

    @Override
    public String getName() {
        return classname;
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
