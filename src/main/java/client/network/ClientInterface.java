package client.network;

import client.UI;
import observer.Observable;
import observer.Observer;
import server.model.Game;
import server.model.Lobby;
import setup.Setup;

import java.io.IOException;
import java.rmi.*;

/**
 * The interface Client.
 */
public interface ClientInterface extends Remote
{
    void send(String string) throws RemoteException;
    void addObserver(Observer<String> observer) throws RemoteException;

    void close() throws IOException, RemoteException;
}
