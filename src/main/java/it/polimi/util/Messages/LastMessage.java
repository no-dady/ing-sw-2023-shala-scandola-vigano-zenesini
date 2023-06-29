package it.polimi.util.Messages;

import it.polimi.client.Client;

import java.rmi.RemoteException;

public class LastMessage implements Message {
    public static final String className = "LastMessage";

    @Override
    public void handleMessage(Client client) {
        try {
            client.getUI().update();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return className;
    }
}
