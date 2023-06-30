package it.polimi.util.Messages;

import it.polimi.client.Client;
import it.polimi.server.model.Game;

/**
 * <p>GameMessage class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class GameMessage implements Message {
    /** Constant <code>className="GameMessage"</code> */
    public static final String className = "GameMessage";
    private final int id;

    /**
     * <p>Constructor for GameMessage.</p>
     *
     * @param id a int
     */
    public GameMessage(int id) {
        this.id = id;
    }

    /** {@inheritDoc} */
    @Override
    public void handleMessage(Client client) {
        Game game = client.getGame();
        game.setCurrPlayerId(id);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return className;
    }
}
