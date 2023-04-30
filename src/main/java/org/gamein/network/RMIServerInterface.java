package org.gamein.network;

import org.gamein.model.Tile;

import java.rmi.*;

public interface RMIServerInterface extends Remote {

    public String send(String string) throws RemoteException;

    public Tile[][] testStrangeObj(Tile[][] shelf) throws RemoteException;
}
