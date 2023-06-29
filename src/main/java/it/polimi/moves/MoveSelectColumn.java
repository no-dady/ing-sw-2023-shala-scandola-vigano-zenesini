package it.polimi.moves;

import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.Game;

public class MoveSelectColumn implements Move {
    private final String nickName;
    private final int lobbyId;
    int selectedColumn;
    public static final String className = "MoveSelectColumn";

    public MoveSelectColumn(String nickName, int lobbyId) {
        this.nickName = nickName;
        this.lobbyId = lobbyId;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getNickName() {
        return this.nickName;
    }

    @Override
    public int getLobbyId() {
        return this.lobbyId;
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
