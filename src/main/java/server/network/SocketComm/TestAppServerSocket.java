package server.network.SocketComm;

import server.network.Server;

import java.io.IOException;
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
    public static void main(String[] args) throws IOException
    {
        ArrayList<Thread> memory = new ArrayList<Thread>();
        ArrayList<ClientSkeleton> clientsList = new ArrayList<ClientSkeleton>();
        Server server = new Server(1900, 1334);
        System.out.println("Server Started");
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            while (true) {
                System.out.println("Waiting connections...");
                Socket socket = serverSocket.accept();
                System.out.println("New connection found");
                notifyAllClients(clientsList);
                ClientSkeleton clientSocketMiddleware = new ClientSkeleton(socket, server);
                //To send the info you have to call the clientSkeleton's function on the server-side
                clientsList.add(clientSocketMiddleware);
                Thread clientSkeletonThread = new Thread(clientSocketMiddleware);
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
        for (ClientSkeleton clientSocketMiddleware : clientsList)
        {
            //clientSocketMiddleware.notifyNewConn("New connection ");
        }
    }
}
