package util.Messages;

import client.network.ClientInterface;
import client.network.State;
import server.model.Game;

public class StateMessage implements Message {

    public static final String className = "StateMessage";
    private final State stateToSend;

    public StateMessage(State state)
    {
        this.stateToSend = state;
    }

    public State getState()
    {
        return stateToSend;
    }

    @Override
    public void handleMessage(ClientInterface clientInterface)
    {
        System.out.println("Prova");
    }

    public String getName()
    {
        return "Boh";
    }
}
