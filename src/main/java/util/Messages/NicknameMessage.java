package util.Messages;

import client.Client;
import client.network.ClientInterface;

public class NicknameMessage implements Message {

    //private final ClientInterface clientInterface;

    private final String nickName;
    public NicknameMessage(String nickName)
    {
        //this.clientInterface = clientInterface;
        this.nickName = nickName;
    }
    //This could be used as handleMessage
    //public ClientInterface getClientInterface()
    //{
    //    return clientInterface;
    //}

    public String getNickName()
    {
        return nickName;
    }

    @Override
    public void handleMessage(Client client)
    {
        System.out.println("Prova");
    }

    public String getName()
    {
        return "Boh";
    }
}
