package client.network;

import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.rmi.*;

/**
 * The interface Client.
 */
public interface ClientInterface extends Remote
{

    /**
     * Test send.
     *
     * @param string the string
     * @throws RemoteException the remote exception
     */
//Test function
    void send(String string) throws RemoteException;

    //void close();
}
