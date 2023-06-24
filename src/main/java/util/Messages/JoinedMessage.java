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

    }

    public String getNicknameJoined()
    {
        return nicknameJoined;
    }

    @Override
    public String getName()
    {
        return "Beta";
    }
}
