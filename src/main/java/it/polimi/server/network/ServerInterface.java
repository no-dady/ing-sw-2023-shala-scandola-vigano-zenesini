package it.polimi.server.network;

import it.polimi.client.network.ClientInterface;

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
    void register(ClientInterface client) throws RemoteException;

    void sendAction(String string) throws RemoteException;

    void sendSetupFirst(String string) throws RemoteException;

    void sendSetupAll(String string) throws RemoteException;
}
