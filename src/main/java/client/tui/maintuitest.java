package client.tui;

import client.Client;
import server.model.Game;

import java.io.IOException;

public class maintuitest {
    public static void main(String args[]) throws IOException {
        Client client = new Client(false);
        client.run();
        client.setGame(new Game());
    }
}
