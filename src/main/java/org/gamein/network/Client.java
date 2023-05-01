package org.gamein.network;

import org.gamein.model.Tile;

import java.rmi.*;

public interface Client extends Remote {
    public void sendChoice(int columnChoice) throws RemoteException;

    public void sendPick(Tile[] tilePick) throws RemoteException;

    public void testSend(String string) throws RemoteException;
}
