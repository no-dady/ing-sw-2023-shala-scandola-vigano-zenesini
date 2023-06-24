package util.Messages;

import client.Client;

public class ErrorMessage implements Message {

    public static final String className = "ErrorMessage";
    private String errorMessage;
    public ErrorMessage(String message)
    {
        this.errorMessage = message;
    }

    @Override
    public void handleMessage(Client client)
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
