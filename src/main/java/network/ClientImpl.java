package network;

import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The type Client.
 */
public class ClientImpl extends UnicastRemoteObject implements Client {
    /**
     * Instantiates a new Client.
     *
     * @param server the server
     * @throws RemoteException the remote exception
     */
    public ClientImpl(Server server) throws RemoteException {
        super();
        initialize(server);
    }

    /**
     * Initialize.
     *
     * @param server the server
     * @throws RemoteException the remote exception
     */
    public void initialize(Server server) throws RemoteException
    {
        server.register(this);
    }

    @Override
    public void sendShelf(Tile[][] shelf)
    {

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
    {
        System.out.println("Ricevuto: " + string);
    }
}
