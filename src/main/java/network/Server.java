package network;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;

import java.rmi.*;

/**
 * The interface Server.
 */
public interface Server extends Remote {

    /**
     * Register.
     *
     * @param client the client
     * @throws RemoteException the remote exception
     */
//Needed to registry (and intercept) the client when it connects to the server
    public void register(Client client) throws RemoteException;

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
    public void testSend(String string) throws RemoteException;
}
