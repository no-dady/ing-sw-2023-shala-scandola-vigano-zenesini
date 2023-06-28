package client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import client.tui.TUI;
import client.gui.GUI;
import client.network.*;
import server.model.Game;

public class Client {
    private UI ui;
    private boolean gui;
    private String connectionType;

    private List<String> playerInLobby = new ArrayList<String>();
    private ClientHandler clientConnection;
    private boolean active = true;
    private boolean online = false;
    private String ip;
    private int port;
    private int lobbyId = -1;
    private State currState = State.WAIT;
    private boolean stateChanged = false;
    public Client(boolean gui) throws IOException {
        this.active = true;
        this.gui = gui;
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

    public void setOnline() throws IOException, RuntimeException, NotBoundException {
        switch (connectionType) {
            case "RMI" -> {
                clientConnection = new ClientHandler(ip, port);
            }
            case "SOCKET" -> {
                clientConnection = new ClientHandler(this, ip, port);
            }
            default -> throw new RuntimeException("Could not initiate connection");
        }

        clientConnection.setClient(this);
        clientConnection.initialize();

        online = true;
        System.out.println("Connection established");
    }


    public void run() throws IOException {
        try {
            if( !gui ) {
                ui = new TUI(this);
                Thread thread = new Thread((Runnable) ui);
                thread.start();
            } else {
                GUI.entry(this);
            }

            synchronized(this) {
                while (isActive()) {
                    this.wait();
                }
            }
        } catch (InterruptedException | NoSuchElementException ex) {
            System.out.println("Connection closed from client side");
        } finally {
            online = false;
            clientConnection.close();

            System.exit(0);
        }
    }

    public void addPlayerInLobby(String nickname)
    {
        playerInLobby.add(nickname);
    }

    public List<String> getPlayerInLobby()
    {
        return playerInLobby;
    }


    public UI getUI() {
        return ui;
    }
    public void setUi(UI ui) {
        this.ui =  ui;
    }

    public boolean isOnline() {
        return online;
    }

    public Game getGame() {
        try {
            return clientConnection.getGame();
        } catch (RemoteException e){}
        return null;
    }
    public ClientHandler getClientConnection() {
        return clientConnection;
    }
    public void setGame(Game game)  {
        try {
            clientConnection.setGame(game);
        }catch (RemoteException e){}
    }

    public void setLobbyId(int lobbyId)
    {
        this.lobbyId = lobbyId;
        System.out.println("Sono in lobby" + lobbyId);
    }

    public int getLobbyId()
    {
        return this.lobbyId;
    }
    public synchronized State getState() {
        return currState;
    }
    public synchronized void setState(State status) {
        this.currState = status;
        this.stateChanged = true;
        try {
            if (ui instanceof GUI) ui.update();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void setStateChanged(boolean b)
    {
        this.stateChanged = b;
    }

    public synchronized boolean getStateChanged()
    {
        return stateChanged;
    }


    public void sendToServer(String parsedString) throws RemoteException
    {
        clientConnection.sendToServer(parsedString);
    }

}
