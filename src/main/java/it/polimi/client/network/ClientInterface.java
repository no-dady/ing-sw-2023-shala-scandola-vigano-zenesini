package it.polimi.client.network;

import java.io.IOException;
import java.rmi.*;

/**
 * The interface Client.
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface ClientInterface extends Remote
{
    /**
     * <p>send.</p>
     *
     * @param string a {@link java.lang.String} object
     * @throws java.rmi.RemoteException if any.
     */
    void send(String string) throws RemoteException;
    /**
     * <p>close.</p>
     *
     * @throws java.io.IOException if any.
     * @throws java.rmi.RemoteException if any.
     */
    void close() throws IOException, RemoteException;
}
