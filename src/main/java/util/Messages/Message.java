package util.Messages;

import client.Client;
import client.network.ClientInterface;

public interface  Message {

    void handleMessage(ClientInterface client);

    String getName();
}
