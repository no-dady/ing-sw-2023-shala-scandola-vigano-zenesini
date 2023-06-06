package server.network.SocketComm;

import client.network.ClientInterface;
import server.network.Server;
import server.network.ServerInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class TestAppServerMultiThreaded {

    public static void main(String[] args)
    {
        Thread rmiThread = new Thread() {
            @Override
            public void run() {
                startRMI();
            }
        };

        rmiThread.start();

        Thread socketThread = new Thread() {
            @Override
            public void run() {
                try
                {
                    startSocket();
                } catch(RemoteException e)
                {
                    System.out.println("Remote exception");
                }
            }
        };

        socketThread.start();

        try {
            rmiThread.join();
            socketThread.join();
        } catch (InterruptedException e)
        {
            System.out.println("No connection protocol available");
        }
    }

    private static void startRMI()
    {
        try
        {
            Server obj = new Server(true);

            LocateRegistry.createRegistry(1900);

            Naming.rebind("rmi://localhost:1900" + "/myShelfie", obj);
            ClientInterface clientInterface = null;
            while (clientInterface == null)
            {
                clientInterface = obj.getClient();
            }
            clientInterface.testSend("Test RMI string from server to client");
        }
        catch (Exception ea)
        {
            System.out.println(ea);
        }
    }

    private static void startSocket() throws RemoteException
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
                for (ClientSkeleton clientSkeleton : clientsList)
                {
                    clientSkeleton.notifyNewConn("Hey " + clientSkeleton.getNickname() + " a new player connected to the lobby");
                }
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
}
