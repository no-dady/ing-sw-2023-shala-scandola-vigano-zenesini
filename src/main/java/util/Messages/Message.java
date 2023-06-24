package util.Messages;

import client.Client;

import java.rmi.RemoteException;

public interface Message {

    void handleMessage(Client client) throws RemoteException;

    String getName();
}
