package network;

import java.io.IOException;

public class Server {
    public Server() {}

    public static void main(String[] args) {
        Server serverLauncher = new Server();
        try {
            serverLauncher.launch();
        } catch (Exception e) {
            System.out.println("> [ERROR] Could not start the server");
        }

    }

    public void launch() throws IOException {
        // TODO: Socket & RMI
        return;
    }


}
