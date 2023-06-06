package client.network;

import client.Client;
import server.network.Server;
import server.network.ServerInterface;
import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * The type Client.
 */
public class ClientHandler extends UnicastRemoteObject implements ClientInterface {
    private ServerInterface serverInterface;
    private Thread socketThread;
    public ClientHandler(String ip, int port) throws RemoteException, MalformedURLException, NotBoundException {
        super();
        Scanner scanner = new Scanner(System.in);

        serverInterface = (ServerInterface) Naming.lookup("rmi://localhost:1900" + "/myShelfie");

        System.out.println("Inserisci una username:");
        String nickName = scanner.next();

        initialize(serverInterface, nickName);
    }

    public ClientHandler(Client client, String ip, int port) throws RemoteException {
        super();
        this.serverInterface = new ClientSocketMiddleware(client, ip, port, this);
    }

    /**
     * Initialize.
     *
     * @param serverInterface the server
     * @throws RemoteException the remote exception
     */
    public void initialize(ServerInterface serverInterface, String nickName) throws RemoteException
    {
        if (serverInterface instanceof ClientSocketMiddleware) {
            socketThread = new Thread((ClientSocketMiddleware) serverInterface);
            socketThread.start();
        } else {
            serverInterface.register(this, nickName);
        }
    }


    @Override
    public void send(String string) throws RemoteException
    {
        System.out.println("Ricevuto: " + string);
    }
}
