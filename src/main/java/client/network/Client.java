package client.network;

import server.network.ServerInterface;
import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * The type Client.
 */
public class Client extends UnicastRemoteObject implements ClientInterface {
    /**
     * Instantiates a new Client.
     *
     * @param serverInterface the server
     * @throws RemoteException the remote exception
     */
    public Client(ServerInterface serverInterface, boolean isRMI) throws RemoteException {
        super();
        Scanner scanner = new Scanner(System.in);

        if (isRMI)
        {
            System.out.println("Inserisci una username:");
            String nickName = scanner.next();

            initialize(serverInterface, nickName);
        }
    }

    /**
     * Initialize.
     *
     * @param serverInterface the server
     * @throws RemoteException the remote exception
     */
    public void initialize(ServerInterface serverInterface, String nickName) throws RemoteException
    {
        serverInterface.register(this, nickName);
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
