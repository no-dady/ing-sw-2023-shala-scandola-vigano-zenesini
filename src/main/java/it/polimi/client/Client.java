package it.polimi.client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import it.polimi.client.tui.TUI;
import it.polimi.client.gui.GUI;
import it.polimi.client.network.*;
import it.polimi.server.model.Game;

/**
 * <p>Client class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class Client {
    private UI ui;
    private final boolean gui;
    private String connectionType;

    private final List<String> playerInLobby = new ArrayList<String>();
    private ClientHandler clientConnection;
    private boolean active = true;
    private boolean online = false;
    private String ip;
    private int port;
    private int lobbyId = -1;
    private State currState = State.WAIT;
    private boolean stateChanged = false;
    /**
     * <p>Constructor for Client.</p>
     *
     * @param gui a boolean
     */
    public Client(boolean gui) {
        this.active = true;
        this.gui = gui;
    }

    /**
     * <p>isActive.</p>
     *
     * @return a boolean
     */
    public synchronized boolean isActive() {
        return this.active;
    }

    /**
     * <p>setConnection.</p>
     *
     * @param ip a {@link java.lang.String} object
     * @param port a int
     * @param connectionType a {@link java.lang.String} object
     */
    public void setConnection(String ip, int port, String connectionType) {
        this.ip = ip;
        this.port = port;
        this.connectionType = connectionType;
    }

    /**
     * <p>Setter for the field <code>active</code>.</p>
     *
     * @param active a boolean
     */
    public synchronized void setActive(boolean active) {
        this.active = active;
        if(!active) notifyAll();
    }

    /**
     * <p>Setter for the field <code>online</code>.</p>
     *
     * @throws java.io.IOException if any.
     * @throws java.lang.RuntimeException if any.
     * @throws java.rmi.NotBoundException if any.
     */
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
        //System.out.println("Connection established");
    }


    /**
     * <p>run.</p>
     *
     * @throws java.io.IOException if any.
     */
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

    /**
     * <p>addPlayerInLobby.</p>
     *
     * @param nickname a {@link java.lang.String} object
     */
    public void addPlayerInLobby(String nickname)
    {
        playerInLobby.add(nickname);
    }

    /**
     * <p>Getter for the field <code>playerInLobby</code>.</p>
     *
     * @return a {@link java.util.List} object
     */
    public List<String> getPlayerInLobby()
    {
        return playerInLobby;
    }


    /**
     * <p>getUI.</p>
     *
     * @return a {@link it.polimi.client.UI} object
     */
    public UI getUI() {
        return ui;
    }
    /**
     * <p>Setter for the field <code>ui</code>.</p>
     *
     * @param ui a {@link it.polimi.client.UI} object
     */
    public void setUi(UI ui) {
        this.ui =  ui;
    }

    /**
     * <p>isOnline.</p>
     *
     * @return a boolean
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * <p>getGame.</p>
     *
     * @return a {@link it.polimi.server.model.Game} object
     */
    public Game getGame() {
        try {
            return clientConnection.getGame();
        } catch (RemoteException ignored){}
        return null;
    }
    /**
     * <p>Getter for the field <code>clientConnection</code>.</p>
     *
     * @return a {@link it.polimi.client.network.ClientHandler} object
     */
    public ClientHandler getClientConnection() {
        return clientConnection;
    }
    /**
     * <p>setGame.</p>
     *
     * @param game a {@link it.polimi.server.model.Game} object
     */
    public void setGame(Game game)  {
        try {
            clientConnection.setGame(game);
        }catch (RemoteException ignored){}
    }

    /**
     * <p>Setter for the field <code>lobbyId</code>.</p>
     *
     * @param lobbyId a int
     */
    public void setLobbyId(int lobbyId)
    {
        this.lobbyId = lobbyId;
        //System.out.println("Sono in lobby" + lobbyId);
    }

    /**
     * <p>Getter for the field <code>lobbyId</code>.</p>
     *
     * @return a int
     */
    public int getLobbyId()
    {
        return this.lobbyId;
    }
    /**
     * <p>getState.</p>
     *
     * @return a {@link it.polimi.client.network.State} object
     */
    public synchronized State getState() {
        return currState;
    }
    /**
     * <p>setState.</p>
     *
     * @param status a {@link it.polimi.client.network.State} object
     */
    public synchronized void setState(State status) {
        this.currState = status;
        this.stateChanged = true;
        if (ui instanceof GUI) ui.update();
    }

    /**
     * <p>Setter for the field <code>stateChanged</code>.</p>
     *
     * @param b a boolean
     */
    public synchronized void setStateChanged(boolean b)
    {
        this.stateChanged = b;
    }

    /**
     * <p>Getter for the field <code>stateChanged</code>.</p>
     *
     * @return a boolean
     */
    public synchronized boolean getStateChanged()
    {
        return stateChanged;
    }


    /**
     * <p>sendToServer.</p>
     *
     * @param parsedString a {@link java.lang.String} object
     * @throws java.rmi.RemoteException if any.
     */
    public void sendToServer(String parsedString) throws RemoteException
    {
        clientConnection.sendToServer(parsedString);
    }

}
