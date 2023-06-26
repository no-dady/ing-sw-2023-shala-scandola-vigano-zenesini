package moves;

import server.model.Bookshelf;
import server.model.Game;

public class MoveSelectColum extends Move {
    int selectedColumn;
    private final String classname= "MoveSelectColumn";

    public MoveSelectColum(String nickName) {
        super(nickName);
    }

    @Override
    public String getName() {
        return classname;
    }

    @Override
    public boolean canPerform(Game game) {
        int selectedY = this.getSelectedColumn();
        if (selectedY < 0 || selectedY > Bookshelf.getCols()) return false;
        if (game.getSelectedTiles() == null) return true;
        return game.getPlayerByNickname(getNickName()).getBookshelf().getEmptyTilesColumn(selectedY) >= game.getSelectedTiles().size();
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }
}
