package util.Messages;

import client.network.ClientInterface;

public class ConfirmMessage implements Message {
    private String message;
    public ConfirmMessage(String message)
    {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void handleMessage(ClientInterface clientInterface)
    {

    }

    @Override
    public String getName()
    {
        return "Beta";
    }
}
