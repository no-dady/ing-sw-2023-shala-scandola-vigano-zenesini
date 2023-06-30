package it.polimi.util.Messages;

import it.polimi.client.Client;

import java.rmi.RemoteException;

public interface Message {

    void handleMessage(Client client);

    String getName();
}
