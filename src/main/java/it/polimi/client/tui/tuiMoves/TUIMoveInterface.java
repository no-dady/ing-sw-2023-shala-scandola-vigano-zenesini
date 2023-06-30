package it.polimi.client.tui.tuiMoves;

import it.polimi.moves.Move;
import it.polimi.server.model.Game;

import java.util.Scanner;

/**
 * <p>TUIMoveInterface interface.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface TUIMoveInterface {

    /**
     * <p>updateCLI.</p>
     *
     * @param game a {@link it.polimi.server.model.Game} object
     * @param stdin a {@link java.util.Scanner} object
     * @return a {@link it.polimi.moves.Move} object
     */
    Move updateCLI(Game game, Scanner stdin);

    /**
     * <p>canPerform.</p>
     *
     * @param game a {@link it.polimi.server.model.Game} object
     * @return a boolean
     */
    boolean canPerform(Game game);

}
