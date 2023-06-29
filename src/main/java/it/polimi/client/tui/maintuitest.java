package it.polimi.client.tui;

import it.polimi.client.Client;

import java.io.IOException;

public class maintuitest {
    public static void main(String args[]) throws IOException {
        Client client = new Client(false);
        client.run();
    }
}
