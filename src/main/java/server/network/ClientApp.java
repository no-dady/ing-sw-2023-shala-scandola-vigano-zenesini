package server.network;

import java.io.IOException;
import java.util.Arrays;

import client.Client;

public class ClientApp {

    public static void main(String[] args) {
        if(args.length < 1) return;

        boolean ui = !Arrays.asList(args).contains("--cli");

        try {
            Client client = new Client(ui);
            client.run();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
