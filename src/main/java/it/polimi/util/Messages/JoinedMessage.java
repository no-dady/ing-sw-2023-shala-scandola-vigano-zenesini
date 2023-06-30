package it.polimi.util.Messages;

import it.polimi.client.Client;

/**
 * <p>JoinedMessage class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class JoinedMessage implements Message {
    /** Constant <code>className="JoinedMessage"</code> */
    public static final String className = "JoinedMessage";
    private final String nicknameJoined;

    private final int lobbyId;

    /**
     * <p>Constructor for JoinedMessage.</p>
     *
     * @param nicknameJoined a {@link java.lang.String} object
     * @param lobbyId a int
     */
    public JoinedMessage(String nicknameJoined, int lobbyId)
    {
        this.nicknameJoined = nicknameJoined;
        this.lobbyId = lobbyId;
    }

    /** {@inheritDoc} */
    @Override
    public void handleMessage(Client client)
    {
        client.getUI().printServerMessage(nicknameJoined + " joined the lobby");
        client.addPlayerInLobby(nicknameJoined);
        client.setLobbyId(lobbyId);
    }

    /** {@inheritDoc} */
    @Override
    public String getName()
    {
        return className;
    }
}
