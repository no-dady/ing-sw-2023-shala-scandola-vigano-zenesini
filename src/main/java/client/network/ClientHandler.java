package client.network;

import client.UI;
import observer.Observer;
import server.model.Game;
import server.model.Lobby;
import server.network.ServerInterface;
import util.Messages.*;
import util.Parser;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import client.Client;

/**
 * The type Client.
 */
public class ClientHandler extends UnicastRemoteObject implements ClientInterface, Serializable {
    private ServerInterface serverInterface;
    private Client client;
    private List<String> playerInLobby;
    private Lobby lobby;

    private int playerCount = 0;

    public void setClient(Client client) {
        this.client = client;
    }

    private Game game;
    private Thread socketThread;
    private String nickName;

    public ClientHandler(String ip, int port) throws RemoteException, MalformedURLException, NotBoundException {
        super();
        this.serverInterface = (ServerInterface) Naming.lookup("rmi://" + ip + ":" + port + "/myShelfie");
        this.playerInLobby = new ArrayList<String>();
    }

    public ClientHandler(Client client, String ip, int port) throws IOException, RemoteException {
        ClientSocketMiddleware clientSocketMiddleware = new ClientSocketMiddleware(client, ip, port, this);
        this.serverInterface = clientSocketMiddleware;
        this.playerInLobby = new ArrayList<String>();
        new Thread(clientSocketMiddleware).start();
    }

    public void initialize() throws RemoteException {
        this.serverInterface.register(this);
    }


    public ServerInterface getServerInterface()
    {
        return this.serverInterface;
    }

    public void sendToServer(String string) throws RemoteException
    {
        client.setState(State.WAITINGFORRESPONSE);
        serverInterface.sendMessage(string);
    }

    @Override
    public void send(String string) throws RemoteException
    {
        System.out.println("Ricevuto: " + string);
        Message msg = Parser.fromJson(string, Message.class);
        msg.handleMessage(this.client);
    }

    public List<String> getPlayerInLobby() {
        return playerInLobby;
    }

    @Override
    public UI getUI() throws RemoteException {
        return null;
    }

    @Override
    public Game getGame() throws RemoteException{
        return this.game;

    }

    @Override
    public void setGame(Game model) throws RemoteException{
        this.game = model;
    }

    public synchronized void setState(State state) throws RemoteException
    {
        this.client.setState(state);
    }

    public synchronized State getState()throws RemoteException {
        return this.client.getState();
    }

    @Override
    public void close() throws IOException {
        this.serverInterface.close();
    }

    @Override
    public Lobby getLobby() {
        return this.lobby;
    }

    @Override
    public void addObserver(Observer<String> observer) {

    }

}
