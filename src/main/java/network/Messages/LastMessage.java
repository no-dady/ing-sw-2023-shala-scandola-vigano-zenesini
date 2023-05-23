package network.Messages;

import network.ClientInterface;

public class LastMessage implements Message {
    String className = "LastMessage";

    @Override
    public void handleMessage(ClientInterface clientInterface) {
        //client.getUI().update();
    }

    @Override
    public String getName() {
        return className;
    }
}
