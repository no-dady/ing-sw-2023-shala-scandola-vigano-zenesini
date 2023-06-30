package it.polimi.client.network;

import it.polimi.observer.Observer;

import java.io.IOException;
import java.rmi.*;

/**
 * The interface Client.
 */
public interface ClientInterface extends Remote
{
    void send(String string) throws RemoteException;
    void close() throws IOException, RemoteException;
}
