package org.gamein.network.RMIComm;

import org.gamein.model.Tile;

import java.rmi.*;

public interface RMIClientInterface extends Remote {
    public void sendChoice(int columnChoice) throws RemoteException;

    public void sendPick(Tile[] tilePick) throws RemoteException;
}
