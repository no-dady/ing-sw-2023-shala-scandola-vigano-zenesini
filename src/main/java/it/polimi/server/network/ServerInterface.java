package it.polimi.server.network;

import it.polimi.client.network.ClientInterface;

import java.io.IOException;
import java.rmi.*;

/**
 * The interface Server.
 */
public interface ServerInterface extends Remote {

    /**
     * Register.
     *
     * @param client the it.polimi.client
     * @throws RemoteException the remote exception
     */
//Needed to registry (and intercept) the it.polimi.client when it connects to the it.polimi.server
    void register(ClientInterface client) throws RemoteException;

    void sendMessage(String string) throws RemoteException;

    void sendSetupFirst(String string) throws RemoteException;

    void sendSetupAll(String string) throws RemoteException;

    void close() throws IOException;
}
