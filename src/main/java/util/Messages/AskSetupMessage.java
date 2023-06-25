package util.Messages;


import client.Client;
import client.network.State;

public class AskSetupMessage implements Message {

    public static final String className = "AskMoveMessage";

    private final State state = State.SETTINGNICKNAME;

    private final int numPlayers = 0;

    @Override
    public void handleMessage(Client client)
    {
        client.setState(numPlayers == 0 ? State.SETUPFIRST : State.SETUP);
    }

    @Override
    public String getName()
    {
        return className;
    }
}
