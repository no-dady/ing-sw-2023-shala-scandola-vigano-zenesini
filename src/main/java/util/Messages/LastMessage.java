package util.Messages;

import client.Client;
import client.network.ClientInterface;

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
