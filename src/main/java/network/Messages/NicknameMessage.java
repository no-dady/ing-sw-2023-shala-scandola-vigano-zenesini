package network.Messages;

import network.Client;

public class NicknameMessage implements Message {

    @Override
    public void handleMessage(Client client)
    {
        System.out.println("Prova");
    }

    public String getName()
    {
        return "BOh";
    }
}
