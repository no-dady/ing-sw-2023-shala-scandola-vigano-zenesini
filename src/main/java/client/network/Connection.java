package client.network;

import java.io.IOException;

import client.Client;
import server.controller.actions.Action;

public interface Connection {

    void sendMove(Action action);

    boolean isOnline();

    boolean setOnline(Client client);

    void closeConnection() throws IOException;

    void sendSetupper();
}

