package org.gamein.network.RMIComm;

import org.gamein.model.Tile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientObject extends UnicastRemoteObject implements RMIClientInterface {
    RMIClientObject() throws RemoteException {
        super();
    }
    public void sendChoice(int columnChoice) throws RemoteException
    {

    }

    public void sendPick(Tile[] tilePick) throws RemoteException
    {

    }
}
