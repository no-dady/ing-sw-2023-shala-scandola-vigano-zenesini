package it.polimi;

import it.polimi.server.network.Server;

import java.io.IOException;
import java.util.Arrays;

public class ServerApp {
    public static void printHelper() {
        System.out.println("SYNTAX:");
        System.out.println("\tjava -jar SERVER_GC49.jar [--rmi | PORT] [--socket | PORT]");
        System.out.println("OPTIONS:");
        System.out.println("\t--rmi\tSpecify rmi port, default is 1900");
        System.out.println("\t--socket\tSpecify socket port, default is 1337");
    }
    public static void main(String[] args) {
        Server server;
        int rmiPort = 1900;
        int socketPort = 1337;

        if(args.length % 2 != 0 || args.length > 4) { //|| !Arrays.asList(args).contains("--rmi")  !Arrays.asList(args).contains("--socket")) {
            printHelper();
            System.exit(0);
        }

        int port = Arrays.asList(args).indexOf("--rmi");
        rmiPort = (port == -1) ? rmiPort : Integer.parseInt(args[port + 1]);
        port = Arrays.asList(args).indexOf("--socket");
        socketPort = (port == -1) ? socketPort : Integer.parseInt(args[port + 1]);

        try {
            server = new Server(rmiPort, socketPort);
            server.run();
        } catch (IOException ex) {
            System.out.println("Could not initialize it.polimi.server:\n" + ex.getMessage());
        }
    }
}
