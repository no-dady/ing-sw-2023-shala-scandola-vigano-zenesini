package util.Messages;


import client.Client;
import client.network.State;

public class AskMoveMessage implements Message {

    private final int moveTypeNumber;

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
        return "Beta";
    }
}
