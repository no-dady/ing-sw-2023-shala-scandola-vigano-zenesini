package it.polimi.util.Messages;

import it.polimi.client.Client;
import it.polimi.server.model.Game;

/**
 * <p>InitialMessage class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class InitialMessage implements Message {
    /** Constant <code>className="InitialMessage"</code> */
    public static final String className = "InitialMessage";
    private final Game game;

    /**
     * <p>Constructor for InitialMessage.</p>
     *
     * @param model a {@link it.polimi.server.model.Game} object
     */
    public InitialMessage(Game model) {
        this.game = model;
    }

    /** {@inheritDoc} */
    @Override
    public void handleMessage(Client client) {
        client.setGame(game);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return className;
    }
}
