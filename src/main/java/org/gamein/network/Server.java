package org.gamein.network;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;

import java.rmi.*;

public interface Server extends Remote {

    //Needed to registry (and intercept) the client when it connects to the server
    public void register(Client client) throws RemoteException;

    //Send the chosen Column number of the client's bookshelf to server
    public void sendChoice(int columnChoice) throws RemoteException;

    //Send the Tile picked from the board from server to server
    public void sendPick(Tile[] tilePick) throws RemoteException;

    //Test function
    public void testSend(String string) throws RemoteException;
}
