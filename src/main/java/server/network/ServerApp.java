package server.network;

import java.io.IOException;
import java.util.Arrays;

public class ServerApp {
    public static void main(String[] args) {
        Server server;

        if(args.length < 1 || !Arrays.asList(args).contains("--rmi") || !Arrays.asList(args).contains("--socket")) {
            System.err.println("Could not initialize server: missing arguments");
            return;
        }

        int rmiPort = Integer.parseInt(args[Arrays.asList(args).indexOf("--rmi") + 1]);
        int socketPort = Integer.parseInt(args[Arrays.asList(args).indexOf("--socket") + 1]);

        try {
            server = new Server(rmiPort, socketPort);
            server.run();
        } catch (IOException ex) {
            System.out.println("Could not initialize server:\n" + ex.getMessage());
        }
    }
}
