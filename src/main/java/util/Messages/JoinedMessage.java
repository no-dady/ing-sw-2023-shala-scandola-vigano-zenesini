package util.Messages;

import client.network.ClientInterface;

public class JoinedMessage implements Message {
    public static final String className = "JoinedMessage";
    private final String nicknameJoined;

    public JoinedMessage(String nicknameJoined)
    {
        this.nicknameJoined = nicknameJoined;
    }

    @Override
    public void handleMessage(ClientInterface clientInterface)
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
