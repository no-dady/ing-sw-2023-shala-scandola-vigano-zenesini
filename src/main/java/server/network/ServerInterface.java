package server.network;

import client.network.ClientInterface;

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
    public void register(ClientInterface client) throws RemoteException;

    public void send(String string) throws RemoteException;
}
