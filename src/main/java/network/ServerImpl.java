package network;

import client.Client;
import network.SocketComm.ClientSkeleton;
import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.*;
import java.rmi.server.*;

/**
 * The type Server.
 */
public class ServerImpl extends UnicastRemoteObject implements Server, Runnable {

    private ServerSocket serverSocket;

    private static final int port = 1234;

    //Array of all matches

    /**
     * Instantiates a new Server.
     *
     * @throws RemoteException the remote exception
     */
    //If we want to use ServerImpl as a socket Server UnicastRemoteObject should be removed (?)
    public ServerImpl() throws RemoteException, IOException {
        super();
        this.serverSocket = new ServerSocket(port);
        //Prepare the Lobby to be filled
    }

    @Override
    public void run()
    {
        System.out.println("Server started");
        System.out.println("IP: " + serverSocket.getLocalSocketAddress() + "\n Port: " + serverSocket.getLocalPort());
        while(true)
        {
            try
            {
                Socket newSocket = serverSocket.accept();
                SocketClientConnection clientSkeleton = new SocketClientConnection(newSocket, this);
                new Thread(clientSkeleton).start();
            } catch (IOException e)
            {
                System.out.println("Connection Error");
            }
        }
    }
    @Override
    public void sendChoice(int columnChoice) throws RemoteException
    {

    }

    @Override
    public void sendPick(Tile[] tilePick) throws RemoteException
    {

    }

    @Override
    public void testSend(String string) throws RemoteException
    {
        System.out.println("Ricevuto: " + string);
    }
}
