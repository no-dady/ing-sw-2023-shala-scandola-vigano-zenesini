package client;

import client.network.ClientHandler;
import client.network.ClientSocketMiddleware;

import java.rmi.RemoteException;

/**
 * The type Test app client socket.
 */
public class TestAppClientSocket {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws RemoteException the remote exception
     */
    public static void main(String[] args) throws RemoteException
    {
        //To send the info you have to call the ServerStub's function on client-side
        Client client = new Client(false);
        ClientHandler clientHandler = new ClientHandler(client, "localhost", 1234);
        ClientSocketMiddleware clientSocketMiddleware = (ClientSocketMiddleware) clientHandler.getServerInterface();
        System.out.println("Starting thread");
        new Thread(clientSocketMiddleware).start();
    }
}
