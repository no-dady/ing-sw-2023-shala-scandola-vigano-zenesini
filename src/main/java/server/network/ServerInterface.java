package server.network;

import client.network.ClientInterface;
import server.model.Tile;

import java.rmi.*;

/**
 * The interface Server.
 */
public interface ServerInterface extends Remote {

    /**
     * Register.
     *
     * @param clientInterface the client
     * @throws RemoteException the remote exception
     */
//Needed to registry (and intercept) the client when it connects to the server
    public void register(ClientInterface clientInterface) throws RemoteException;

    /**
     * Send choice.
     *
     * @param columnChoice the column choice
     * @throws RemoteException the remote exception
     */
//Send the chosen Column number of the client's bookshelf to server
    public void sendChoice(int columnChoice) throws RemoteException;

    /**
     * Send pick.
     *
     * @param tilePick the tile pick
     * @throws RemoteException the remote exception
     */
//Send the Tile picked from the board from server to server
    public void sendPick(Tile[] tilePick) throws RemoteException;

    /**
     * Test send.
     *
     * @param string the string
     * @throws RemoteException the remote exception
     */
//Test function
    public void send(String string) throws RemoteException;
}
