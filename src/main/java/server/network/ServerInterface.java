package server.network;

import client.network.ClientInterface;

import java.io.IOException;
import java.rmi.*;

/**
 * The interface Server.
 */
public interface ServerInterface extends Remote {

    /**
     * Register.
     *
     * @param client the client
     * @throws RemoteException the remote exception
     */
//Needed to registry (and intercept) the client when it connects to the server
    void register(ClientInterface client) throws RemoteException;

    void sendMessage(String string) throws RemoteException;

    void sendSetup(String string) throws RemoteException;

    void close() throws IOException;
}
