package it.polimi.util.Messages;

import it.polimi.client.Client;

public class NicknameMessage implements Message {

    //private final ClientInterface clientInterface;
    public static final String className = "NicknameMessage";

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
        return className;
    }
}
