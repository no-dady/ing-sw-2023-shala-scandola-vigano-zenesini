package network.SocketComm;

import network.ClientInterface;
import network.ServerInterface;
import network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

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
            Server obj = new Server();

            LocateRegistry.createRegistry(1900);

            Naming.rebind("rmi://localhost:1900" + "/myShelfie", obj);
            ClientInterface clientInterface = null;
            System.out.println("Entro");
            while (clientInterface == null)
            {
                clientInterface = obj.getClient();
            }
            System.out.println("Uscito");
            clientInterface.testSend("Test RMI string from server to client");
        }
        catch (Exception ea)
        {
            System.out.println(ea);
        }
    }

    private static void startSocket() throws RemoteException
    {
        try (ServerSocket serverSocket = new ServerSocket(1234))
        {
            while (true)
            {
                try (Socket socket = serverSocket.accept())
                {
                    ClientSkeleton clientSkeleton = new ClientSkeleton(socket);
                    ServerInterface serverInterface = new Server();
                    //To send the info you have to call the clientSkeleton's function on the server-side
                    clientSkeleton.testSend("Test Socket string from server to client");
                    serverInterface.register(clientSkeleton, "protoro");
                    while (true) {
                        clientSkeleton.receive(serverInterface);
                    }
                } catch (IOException e)
                {
                    System.out.println("Socket failed: " + e.getMessage() + ". Closing connection and wating for new one");
                }
            }
        } catch (IOException e)
        {
            throw new RemoteException("Cannot create server socket", e);
        }
    }
}
