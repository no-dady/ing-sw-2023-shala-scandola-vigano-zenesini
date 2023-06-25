package util.Messages;

import client.Client;
import client.network.ClientInterface;
import client.network.State;

public class StateMessage implements Message {

    public static final String className = "StateMessage";
    private final State stateToSend;

    public StateMessage(State state)
    {
        this.stateToSend = state;
    }

    @Override
    public void handleMessage(Client client)
    {
        client.setState(stateToSend);
    }

    public String getName()
    {
        return "Boh";
    }
}
