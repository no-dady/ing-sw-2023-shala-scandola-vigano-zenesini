package it.polimi.client.gui.guiMoves;

import it.polimi.moves.Move;
import it.polimi.moves.MoveSelectTiles;
import it.polimi.server.model.Game;

/**
 * <p>GUISelectTiles class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class GUISelectTiles implements GUIMoveInterface {
    private final MoveSelectTiles move;
    private final String selectedTiles;
    /**
     * <p>Constructor for GUISelectTiles.</p>
     *
     * @param nickName a {@link java.lang.String} object
     * @param lobbyId a int
     * @param selectedTiles a {@link java.lang.String} object
     */
    public GUISelectTiles(String nickName, int lobbyId, String selectedTiles){
        this.move = new MoveSelectTiles(nickName, lobbyId);
        this.selectedTiles = selectedTiles;
    }
    /** {@inheritDoc} */
    @Override
    public Move updateGUI(Game game) {
        move.setSelectedTiles(selectedTiles);
        if (canPerform(game)) return move;
        else return null;
    }

    /** {@inheritDoc} */
    @Override
    public boolean canPerform(Game game) {
        return move.canPerform(game);
    }

}
