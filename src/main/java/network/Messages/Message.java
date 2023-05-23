package network.Messages;

import network.ClientInterface;

public interface  Message {

    void handleMessage(ClientInterface clientInterface);

    String getName();
}
