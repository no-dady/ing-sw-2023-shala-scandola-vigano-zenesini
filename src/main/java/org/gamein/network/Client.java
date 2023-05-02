package org.gamein.network;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;

import java.rmi.*;

public interface Client extends Remote
{
    //Send the shelf from server to client
    public void sendShelf(Tile[][] shelf) throws RemoteException;

    //Send the Board from server to client
    public void sendBoard(Board board) throws RemoteException;

    //Send the Bookshelf from server to client
    public void sendBookshelf(Bookshelf bookshelf) throws RemoteException;

    //Test function
    public void testSend(String string) throws RemoteException;
}
