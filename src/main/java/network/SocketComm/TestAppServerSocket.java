package network.SocketComm;

import network.ServerInterface;
import network.Server;

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
                    ServerInterface serverInterface = new Server(false);
                    //To send the info you have to call the clientSkeleton's function on the server-side
                    clientSkeleton.testSend("Test Socket string from server to client");
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
