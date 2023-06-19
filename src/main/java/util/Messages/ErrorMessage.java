package util.Messages;

import client.network.ClientInterface;

public class ErrorMessage implements Message {

    public static final String className = "ErrorMessage";
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
        return className;
    }
    public String getErrorMessage()
    {
        return errorMessage;
    }

}
