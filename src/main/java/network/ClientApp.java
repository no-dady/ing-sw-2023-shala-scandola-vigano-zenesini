package network;

import java.io.IOException;
import java.util.Arrays;

import client.Client;
import client.UI;
import client.TUI;
import client.GUI;

public class ClientApp {

    public static void main(String[] args) {
        if(args.length < 1) return;

        UI ui = Arrays.asList(args).contains("--cli") ? new TUI() : new GUI();

        Client client = new Client(ui);

        try {
            client.run();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
