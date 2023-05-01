package org.gamein.network;

import org.gamein.network.RMIComm.RMIClientObject;
import org.gamein.network.SocketComm.ServerStub;

import java.io.IOException;
import java.rmi.RemoteException;

public class TestAppClientSocket {
    public static void main(String[] args) throws RemoteException
    {
        ServerStub serverStub = new ServerStub("localhost", 1234);
        RMIClientObject client = new RMIClientObject(serverStub);
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
    }
}
