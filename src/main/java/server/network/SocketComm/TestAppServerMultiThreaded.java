package server.network.SocketComm;

import client.network.ClientInterface;
import server.model.Lobby;
import server.network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

public class TestAppServerMultiThreaded {

    public static void main(String[] args)
    {
        List<Lobby> lobbyList = new ArrayList<Lobby>();
        Thread rmiThread = new Thread() {
            @Override
            public void run() {
                startRMI(lobbyList);
            }
        };

        rmiThread.start();

        Thread socketThread = new Thread() {
            @Override
            public void run() {
                try
                {
                    startSocket(lobbyList);
                } catch(Exception e)
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

    private static void startRMI(List<Lobby> lobbyList)
    {
        try
        {
            Server obj = new Server(1900, 1334);

            LocateRegistry.createRegistry(1900);

            Naming.rebind("rmi://localhost:1900" + "/myShelfie", obj);
        }
        catch (Exception ea)
        {
            System.out.println(ea);
        }
    }

    private static void startSocket(List<Lobby> lobbyList) throws RemoteException, IOException
    {
        ArrayList<Thread> memory = new ArrayList<Thread>();
        ArrayList<ClientSkeleton> clientsList = new ArrayList<ClientSkeleton>();
        Server serverInterface = new Server(1900, 1337);
        System.out.println("Server Started");
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            while (true) {
                System.out.println("Waiting connections...");
                Socket socket = serverSocket.accept();
                System.out.println("New connection found");
                ClientSkeleton clientSocketMiddleware = new ClientSkeleton(socket, serverInterface);
                //To send the info you have to call the clientSkeleton's function on the server-side
                //clientsList.add(clientSkeleton);
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
}
