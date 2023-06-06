package client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import client.tui.TUI;
import client.gui.GUI;
import client.network.*;

public class Client {
    private final UI ui;
    private String connectionType;
    private ClientInterface clientConnection;
    private boolean active = true;
    private boolean online = false;
    private String ip;
    private int port;

    public Client(boolean gui) throws IOException {
        this.active = true;
        this.ui = gui ? new GUI() : new TUI(this);
    }

    public synchronized boolean isActive() {
        return this.active;
    }

    public void setConnection(String ip, int port, String connectionType) {
        this.ip = ip;
        this.port = port;
        this.connectionType = connectionType;
    }

    public synchronized void setActive(boolean active) {
        this.active = active;
        if(!active) notifyAll();
    }

    public void setOnline() throws RemoteException, RuntimeException {
        switch (connectionType) {
            case "RMI" -> clientConnection = new RmiClientConnection(ip, port);
            case "SOCKET" -> clientConnection = new SocketClientConnection(this, ip, port);
            default -> throw new RuntimeException("Could not initiate connection");
        }

        online = true;
        System.out.println("Connection established");
    }

    public void run() throws IOException {
        try {
            if(ui instanceof TUI) {
                Thread thread = new Thread((Runnable) ui);
                thread.start();
            } else {
               // ui.entry();
            }

            synchronized(this) {
                while (isActive()) {
                    this.wait();
                }
            }
        } catch (InterruptedException | NoSuchElementException ex) {
            System.out.println("Connection closed from client side");
        } finally {
            clientConnection.close();

            System.exit(0);
        }
    }

    public boolean isOnline() {
        return online;
    }
}
