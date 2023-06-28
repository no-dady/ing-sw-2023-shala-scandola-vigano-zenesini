package moves;

import server.model.Bookshelf;
import server.model.Game;

public class MoveSelectColum extends Move {
    public static String className;
    int selectedColumn;
    public final String classname= "MoveSelectColumn";

    public MoveSelectColum(String nickName, int lobbyId) {
        super(nickName, lobbyId);
    }

    @Override
    public String getClassName() {
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
