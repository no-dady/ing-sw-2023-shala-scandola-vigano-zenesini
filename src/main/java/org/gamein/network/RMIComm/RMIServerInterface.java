package org.gamein.network.RMIComm;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;

import java.rmi.*;

public interface RMIServerInterface extends Remote {

    public void register(RMIClientInterface client)  throws RemoteException;;

    public void sendShelf(Tile[][] shelf) throws RemoteException;

    public void sendBoard(Board board) throws RemoteException;

    public void sendBookshelf(Bookshelf bookshelf) throws RemoteException;

    public void testSend(String string) throws RemoteException;
}
