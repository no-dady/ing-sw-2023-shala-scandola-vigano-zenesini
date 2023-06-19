package util.Messages;

import client.Client;
import client.network.ClientInterface;

import java.rmi.RemoteException;

public interface  Message {

    void handleMessage(ClientInterface client) throws RemoteException;

    String getName();
}
