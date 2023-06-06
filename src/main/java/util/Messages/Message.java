package util.Messages;

import client.Client;

public interface  Message {

    void handleMessage(Client client);

    String getName();
}
