package util.Messages;

import client.network.ClientInterface;

public class AskMoveMessage implements Message {

    private final int moveTypeNumber;

    public AskMoveMessage(int moveTypeNumber)
    {
        this.moveTypeNumber = moveTypeNumber;
    }

    @Override
    public void handleMessage(ClientInterface clientInterface)
    {

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
