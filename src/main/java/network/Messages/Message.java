package network.Messages;

import network.Client;

public interface  Message {

    void handleMessage(Client client);

    String getName();
}
