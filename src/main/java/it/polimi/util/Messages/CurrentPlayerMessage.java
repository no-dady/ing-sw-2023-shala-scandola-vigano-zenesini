package it.polimi.util.Messages;

import it.polimi.client.Client;

/**
 * <p>CurrentPlayerMessage class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class CurrentPlayerMessage implements Message {
    /** Constant <code>className="CurrentPlayerMessage"</code> */
    public static final String className = "CurrentPlayerMessage";
    private final String currentPlayerNickname;

    /**
     * <p>Constructor for CurrentPlayerMessage.</p>
     *
     * @param currentPlayerNickname a {@link java.lang.String} object
     */
    public CurrentPlayerMessage(String currentPlayerNickname) {
        this.currentPlayerNickname = currentPlayerNickname;
    }

    /**
     * <p>Getter for the field <code>currentPlayerNickname</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getCurrentPlayerNickname() {
        return currentPlayerNickname;
    }

    /** {@inheritDoc} */
    @Override
    public void handleMessage(Client client) {
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return className;
    }
}
