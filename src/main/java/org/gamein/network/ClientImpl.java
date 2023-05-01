package org.gamein.network;

import org.gamein.model.Tile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client {
    public ClientImpl(Server server) throws RemoteException {
        super();
        initialize(server);
    }

    public void initialize(Server server) throws RemoteException
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
