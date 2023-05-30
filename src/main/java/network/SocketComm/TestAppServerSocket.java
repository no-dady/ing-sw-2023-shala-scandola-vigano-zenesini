package network.SocketComm;

import network.Client;
import network.ClientInterface;
import network.ServerInterface;
import network.Server;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The type Test app server socket.
 */
public class TestAppServerSocket {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws RemoteException the remote exception
     */
    public static void main(String[] args) throws RemoteException
    {
        ArrayList<Thread> memory = new ArrayList<Thread>();
        ArrayList<ClientSkeleton> clientsList = new ArrayList<ClientSkeleton>();
        ServerInterface serverInterface = new Server(false);
        System.out.println("Server Started");
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            while (true) {
                System.out.println("Waiting connections...");
                Socket socket = serverSocket.accept();
                System.out.println("New connection found");
                notifyAllClients(clientsList);
                ClientSkeleton clientSkeleton = new ClientSkeleton(socket, serverInterface);
                //To send the info you have to call the clientSkeleton's function on the server-side
                clientsList.add(clientSkeleton);
                Thread clientSkeletonThread = new Thread(clientSkeleton);
                memory.add(clientSkeletonThread);
                clientSkeletonThread.start();
                System.out.println("Thread launched from main");
            }
        } catch (IOException e)
        {
            throw new RemoteException("Cannot create server socket", e);
        }
    }

    public static void notifyAllClients(ArrayList<ClientSkeleton> clientsList)
    {
        for (ClientSkeleton clientSkeleton : clientsList)
        {
            clientSkeleton.notifyNewConn("New connection ");
        }
    }
}
