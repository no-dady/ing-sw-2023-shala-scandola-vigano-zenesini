package org.gamein.network.RMIComm;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;

import java.rmi.*;
import java.rmi.server.*;

public class RMIServerObject extends UnicastRemoteObject implements RMIServerInterface {
    public RMIServerObject() throws RemoteException {
        super();
    }

    @Override
    public void register(RMIClientInterface client)
    {

    }

    @Override
    public void sendShelf(Tile[][] shelf)
    {
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 5; j++) {
                System.out.print(shelf[i][j].getTileType() + "\t|\t");
            }
            System.out.println();
        }
    }

    @Override
    public void sendBoard(Board board) throws RemoteException
    {

    }

    @Override
    public void sendBookshelf(Bookshelf bookshelf) throws RemoteException
    {

    }

    @Override
    public void testSend(String string) throws RemoteException
    {}
}
