package network;

import client.Client;

public class LastMessage implements Message {
    String className = "LastMessage";

    @Override
    public void handleMessage(RMIClientClass client) {
        client.getUI().update();
    }

    @Override
    public String getName() {
        return className;
    }
}
