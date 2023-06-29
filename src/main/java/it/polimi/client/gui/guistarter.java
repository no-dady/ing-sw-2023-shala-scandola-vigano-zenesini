package it.polimi.client.gui;

import it.polimi.client.Client;

import java.io.IOException;

public class guistarter {
    public static void main(String[] args) throws IOException {
        Client client = new Client(true);
        client.run();
    }
}
