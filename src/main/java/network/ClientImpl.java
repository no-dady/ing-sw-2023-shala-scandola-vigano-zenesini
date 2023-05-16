package network;

import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

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
    public ClientImpl(Server server, boolean isRMI) throws RemoteException {
        super();
        Scanner scanner = new Scanner(System.in);

        if (isRMI)
        {
            System.out.println("Inserisci una username:");
            String nickName = scanner.next();

            initialize(server, nickName);
        }
    }

    /**
     * Initialize.
     *
     * @param server the server
     * @throws RemoteException the remote exception
     */
    public void initialize(Server server, String nickName) throws RemoteException
    {
        server.register(this, nickName);
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
