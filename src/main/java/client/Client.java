package client;

import java.io.IOException;
import java.util.NoSuchElementException;

import network.ClientInterface;

public class Client {
    private final UI ui;
    private ClientInterface clientConnection;
    private boolean active = false;
    private boolean online = false;

    public Client(UI ui) {
        this.active = true;
        this.ui = ui;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setConnection(ClientInterface clientConnection) {
        this.clientConnection = clientConnection;
    }


    public void run() throws IOException {
        try {
            if(ui instanceof TUI) {
                Thread thread = new Thread(ui);
                thread.start();
            } else {
                ui.entry();
            }

            synchronized(this) {
                while (isActive()) {
                    this.wait();
                }
            }
        } catch (InterruptedException | NoSuchElementException ex) {
            System.out.println("Connection closed from client side");
        } finally {
            if(online) {
                clientConnection.close();
            }

            System.exit(0);
        }
    }
}
