package org.gamein.network.RMIComm;

import org.gamein.model.Tile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientObject extends UnicastRemoteObject implements RMIClientInterface {
    public RMIClientObject(RMIServerInterface server) throws RemoteException {
        super();
        initialize(server);
    }

    public void initialize(RMIServerInterface server) throws RemoteException
    {
        server.register(this);
    }

    public void sendChoice(int columnChoice) throws RemoteException
    {

    }

    public void sendPick(Tile[] tilePick) throws RemoteException
    {

    }

    public void testSend(String string) throws RemoteException
    {

    }
}
