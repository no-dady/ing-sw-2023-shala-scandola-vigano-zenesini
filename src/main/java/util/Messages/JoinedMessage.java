package util.Messages;

import client.Client;
import client.network.ClientInterface;

public class JoinedMessage implements Message {
    public static final String className = "JoinedMessage";
    private final String nicknameJoined;

    public JoinedMessage(String nicknameJoined)
    {
        this.nicknameJoined = nicknameJoined;
    }

    @Override
    public void handleMessage(Client client)
    {
        client.getUI().printServerMessage(nicknameJoined + " joined the lobby");
        client.addPlayerInLobby(nicknameJoined);
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
