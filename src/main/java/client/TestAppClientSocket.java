package client;

import client.network.Client;
import client.network.ServerStub;

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
        ServerStub serverStub = new ServerStub("localhost", 1234);
        Client client = new Client(serverStub, false);
        serverStub.setClientinterface(client);
        new Thread(serverStub).start();
        serverStub.testContinousSend();
    }
}
