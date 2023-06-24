package client.network;

import client.UI;
import observer.Observer;
import server.model.Game;

import java.rmi.*;

/**
 * The interface Client.
 */
public interface ClientInterface extends Remote
{

    void addObserver(Observer<String> observer) throws RemoteException;

    void notify(String message) throws RemoteException;

    void send(String string) throws RemoteException;

    UI getUI() throws RemoteException;

    Game getGame() throws RemoteException;

    void setGame(Game game) throws RemoteException;

    State getState() throws RemoteException;

    //void close();
}
