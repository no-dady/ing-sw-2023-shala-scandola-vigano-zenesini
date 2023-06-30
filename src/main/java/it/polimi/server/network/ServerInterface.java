package it.polimi.server.network;

import it.polimi.client.network.ClientInterface;

import java.rmi.*;

/**
 * The interface Server.
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface ServerInterface extends Remote {

    /**
     * Register.
     *
     * @param client the it.polimi.client
     * @throws java.rmi.RemoteException the remote exception
     */
    void register(ClientInterface client) throws RemoteException;

    /**
     * <p>sendAction.</p>
     *
     * @param string a {@link java.lang.String} object
     * @throws java.rmi.RemoteException if any.
     */
    void sendAction(String string) throws RemoteException;

    /**
     * <p>sendSetupFirst.</p>
     *
     * @param string a {@link java.lang.String} object
     * @throws java.rmi.RemoteException if any.
     */
    void sendSetupFirst(String string) throws RemoteException;

    /**
     * <p>sendSetupAll.</p>
     *
     * @param string a {@link java.lang.String} object
     * @throws java.rmi.RemoteException if any.
     */
    void sendSetupAll(String string) throws RemoteException;
}
