package util.Messages;

import client.Client;
import client.network.ClientInterface;

public class LastMessage implements Message {
    public static final String className = "LastMessage";

    @Override
    public void handleMessage(Client client) {
        client.getUI().update();
    }

    @Override
    public String getName() {
        return className;
    }
}
