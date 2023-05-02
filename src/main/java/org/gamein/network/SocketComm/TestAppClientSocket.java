package org.gamein.network.SocketComm;

import org.gamein.network.ClientImpl;

import java.io.IOException;
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
        new Thread() {
            public void run()
            {
                ServerStub serverStub = new ServerStub("localhost", 1234);
                try {
                    ClientImpl client = new ClientImpl(serverStub);
                    serverStub.testSend("Test Socket string from client to server");
                    while (true) {
                        try {
                            serverStub.receive(client);
                        } catch (IOException e)
                        {
                            System.err.println("Cannot receive from server. Stopping...");
                            try {
                                serverStub.close();
                            } catch (RemoteException ex) {
                                System.err.println("Cannot close connection with server. Halting...");
                            }
                            System.exit(1);
                        }
                    }
                } catch (RemoteException e) { System.out.println(e); }
            }
        }.start();
    }
}
