package client.gui;

import client.Client;
import javafx.application.Application;

import java.io.IOException;

public class guistarter {
    public static void main(String[] args) throws IOException {
        Client client = new Client(true);
        client.run();

    }
}
