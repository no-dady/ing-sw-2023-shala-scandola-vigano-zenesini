package util.Messages;


import client.Client;
import client.network.State;

public class AskMoveMessage implements Message {

    public static final String className = "AskMoveMessage";
    private final int moveTypeNumber;
    private final State state = State.SETTINGNICKNAME;

    public AskMoveMessage(int moveTypeNumber)
    {
        this.moveTypeNumber = moveTypeNumber;
    }

    @Override
    public void handleMessage(Client client)
    {
        client.setState(State.SETTINGNICKNAME);
    }

    public int getMoveTypeNumber()
    {
        return moveTypeNumber;
    }

    @Override
    public String getName()
    {
        return className;
    }
}
