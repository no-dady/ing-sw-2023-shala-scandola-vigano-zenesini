package it.polimi.client.network;

import it.polimi.observer.Observer;
import it.polimi.server.model.Game;
import it.polimi.server.model.Lobby;
import it.polimi.server.network.ServerInterface;
import it.polimi.util.Messages.*;
import it.polimi.util.Parser;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.client.Client;

/**
 * The type Client.
 */
public class ClientHandler extends UnicastRemoteObject implements ClientInterface, Serializable {
    private ServerInterface serverInterface;
    private Client client;
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
    }

    public ClientHandler(Client client, String ip, int port) throws IOException, RemoteException {
        ClientSocketMiddleware clientSocketMiddleware = new ClientSocketMiddleware(client, ip, port, this);
        this.serverInterface = clientSocketMiddleware;
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

    public Game getGame() throws RemoteException{
        return this.game;

    }

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

    public Lobby getLobby() {
        return this.lobby;
    }

    @Override
    public void addObserver(Observer<String> observer) {

    }
}
