package org.gamein.network.SocketComm;

import org.gamein.network.Client;
import org.gamein.network.Server;
import org.gamein.network.ServerImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

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
        try (ServerSocket serverSocket = new ServerSocket(1234))
        {
            while (true)
            {
                try (Socket socket = serverSocket.accept())
                {
                    ClientSkeleton clientSkeleton = new ClientSkeleton(socket);
                    Server server = new ServerImpl();
                    //To send the info you have to call the clientSkeleton's function on the server-side
                    clientSkeleton.testSend("Test Socket string from server to client");
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
