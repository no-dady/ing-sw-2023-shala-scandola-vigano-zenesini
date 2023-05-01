package org.gamein.network;

import org.gamein.network.RMIComm.RMIServerInterface;
import org.gamein.network.RMIComm.RMIServerObject;
import org.gamein.network.SocketComm.ClientSkeleton;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Objects;

public class TestAppServerSocket {
    public static void main(String[] args) throws RemoteException
    {
        try (ServerSocket serverSocket = new ServerSocket(1234))
        {
            while (true)
            {
                try (Socket socket = serverSocket.accept())
                {
                    ClientSkeleton clientSkeleton = new ClientSkeleton(socket);
                    RMIServerInterface server = new RMIServerObject();
                    while (true) {
                        clientSkeleton.receive(server);
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
