package util.Messages;

import client.Client;
import client.network.ClientInterface;

public class JoinedMessage implements Message {
    public static final String className = "JoinedMessage";
    private final String nicknameJoined;

    private final int lobbyId;

    public JoinedMessage(String nicknameJoined, int lobbyId)
    {
        this.nicknameJoined = nicknameJoined;
        this.lobbyId = lobbyId;
    }

    @Override
    public void handleMessage(Client client)
    {
        client.getUI().printServerMessage(nicknameJoined + " joined the lobby");
        client.addPlayerInLobby(nicknameJoined);
        client.setLobbyId(lobbyId);
    }

    public String getNicknameJoined()
    {
        return nicknameJoined;
    }

    @Override
    public String getName()
    {
        return className;
    }
}
