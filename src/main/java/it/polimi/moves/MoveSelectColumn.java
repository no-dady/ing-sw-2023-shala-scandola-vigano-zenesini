package it.polimi.moves;

import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.Game;

/**
 * <p>MoveSelectColumn class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class MoveSelectColumn implements Move {
    private final String nickName;
    private final int lobbyId;
    int selectedColumn;
    /** Constant <code>className="MoveSelectColumn"</code> */
    public static final String className = "MoveSelectColumn";

    /**
     * <p>Constructor for MoveSelectColumn.</p>
     *
     * @param nickName a {@link java.lang.String} object
     * @param lobbyId a int
     */
    public MoveSelectColumn(String nickName, int lobbyId) {
        this.nickName = nickName;
        this.lobbyId = lobbyId;
    }

    /** {@inheritDoc} */
    @Override
    public String getClassName() {
        return className;
    }

    /** {@inheritDoc} */
    @Override
    public String getNickName() {
        return this.nickName;
    }

    /** {@inheritDoc} */
    @Override
    public int getLobbyId() {
        return this.lobbyId;
    }

    /** {@inheritDoc} */
    @Override
    public boolean canPerform(Game game) {
        int selectedY = this.getSelectedColumn();
        if (selectedY < 0 || selectedY > Bookshelf.getCols()) return false;
        if (game.getSelectedTiles() == null) return true;
        return game.getPlayerByNickname(getNickName()).getBookshelf().getEmptyTilesColumn(selectedY) >= game.getSelectedTiles().size();
    }

    /**
     * <p>Getter for the field <code>selectedColumn</code>.</p>
     *
     * @return a int
     */
    public int getSelectedColumn() {
        return selectedColumn;
    }

    /**
     * <p>Setter for the field <code>selectedColumn</code>.</p>
     *
     * @param selectedColumn a int
     */
    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }

}
