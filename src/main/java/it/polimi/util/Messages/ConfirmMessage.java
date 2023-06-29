package it.polimi.util.Messages;

import it.polimi.client.Client;
import it.polimi.client.network.ClientHandler;

public class ConfirmMessage implements Message {
    public static final String className = "ConfirmMessage";

    //0: Joined as Admin
    //1: Joined as Regular
    //2: Someone else joined your lobby
    //3: Joined the lobby as the last one
    //4: Starting lobby
    //5: Received game model
    private final int confirmNumber;
    private String message;
    public ConfirmMessage(String message, int confirmNumber)
    {
        this.message = message;
        this.confirmNumber = confirmNumber;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void handleMessage(Client client)
    {
        ClientHandler cli = client.getClientConnection();
        cli.getLobby().getPlayerNumber();
    }

    @Override
    public String getName()
    {
        return className;
    }
}
