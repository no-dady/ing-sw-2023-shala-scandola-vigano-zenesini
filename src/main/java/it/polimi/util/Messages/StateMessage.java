package it.polimi.util.Messages;

import it.polimi.client.Client;
import it.polimi.client.network.State;

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
        return className;
    }
}
