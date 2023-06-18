package util.Messages;

import client.network.ClientInterface;

public class ErrorMessage implements Message {

    private String errorMessage;
    public ErrorMessage(String message)
    {
        this.errorMessage = message;
    }

    @Override
    public void handleMessage(ClientInterface clientInterface)
    {

    }

    @Override
    public String getName()
    {
        return "boh";
    }
    public String getErrorMessage()
    {
        return errorMessage;
    }

}
