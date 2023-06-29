package it.polimi.util.Messages;

import it.polimi.client.Client;
import it.polimi.server.model.Board;

import java.rmi.RemoteException;

public class BoardMessage implements Message{
    public static final String className = "BoardMessage";
    private Board board;

    public BoardMessage(Board board)
    {
        this.board = board;
    }

    @Override
    public void handleMessage(Client client) throws RemoteException
    {
        client.getGame().setBoard(board);
    }


    public String getName()
    {
        return className;
    }
}
