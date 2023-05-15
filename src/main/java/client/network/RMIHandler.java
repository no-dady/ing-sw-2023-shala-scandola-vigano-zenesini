package client.network;

import java.io.Serializable;
import java.rmi.RemoteException;

import client.Client;
import server.controller.actions.Action;

public class RMIHandler implements Connection {
    @Override
    public void sendSetupper() {}
    @Override
    public void closeConnection() {}
    @Override
    public void sendMove(Action action) {}
    @Override
    public boolean setOnline(Client client) { return false; }
    @Override
    public boolean isOnline() { return false; }

    String getMessage() throws RemoteException { return new String(); }
}
