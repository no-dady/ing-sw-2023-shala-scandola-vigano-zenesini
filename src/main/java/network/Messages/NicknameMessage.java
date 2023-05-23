package network.Messages;

import network.ClientInterface;

public class NicknameMessage implements Message {

    @Override
    public void handleMessage(ClientInterface clientInterface)
    {
        System.out.println("Prova");
    }

    public String getName()
    {
        return "BOh";
    }
}
