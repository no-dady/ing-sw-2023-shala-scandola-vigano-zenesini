package it.polimi.client.tui.tuiMoves;

import it.polimi.setup.Setup;

import java.util.Scanner;

/**
 * <p>TUISetupInterface interface.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface TUISetupInterface {
    /**
     * <p>create.</p>
     *
     * @param scanner a {@link java.util.Scanner} object
     * @param firstTime a boolean
     * @return a {@link it.polimi.setup.Setup} object
     */
    Setup create(Scanner scanner, boolean firstTime);

}
