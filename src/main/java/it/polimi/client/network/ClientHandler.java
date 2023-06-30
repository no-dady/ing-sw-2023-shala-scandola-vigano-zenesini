package it.polimi.client.network;

import it.polimi.server.model.Game;
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
 *
 * @author daniel
 * @version $Id: $Id
 */
public class ClientHandler extends UnicastRemoteObject implements ClientInterface, Serializable {
    private final ServerInterface serverInterface;
    private Client client;
    private final boolean isRmi;
    /**
     * <p>Setter for the field <code>client</code>.</p>
     *
     * @param client a {@link it.polimi.client.Client} object
     */
    public void setClient(Client client) {
        this.client = client;
    }
    private Game game;

    /**
     * <p>Constructor for ClientHandler.</p>
     *
     * @param ip a {@link java.lang.String} object
     * @param port a int
     * @throws java.rmi.RemoteException if any.
     * @throws java.net.MalformedURLException if any.
     * @throws java.rmi.NotBoundException if any.
     */
    public ClientHandler(String ip, int port) throws RemoteException, MalformedURLException, NotBoundException {
        super();
        this.serverInterface = (ServerInterface) Naming.lookup("rmi://" + ip + ":" + port + "/myShelfie");
        this.isRmi = true;
    }

    /**
     * <p>Constructor for ClientHandler.</p>
     *
     * @param client a {@link it.polimi.client.Client} object
     * @param ip a {@link java.lang.String} object
     * @param port a int
     * @throws java.io.IOException if any.
     * @throws java.rmi.RemoteException if any.
     */
    public ClientHandler(Client client, String ip, int port) throws IOException, RemoteException {
        ClientSocketMiddleware clientSocketMiddleware = new ClientSocketMiddleware(client, ip, port, this);
        this.serverInterface = clientSocketMiddleware;
        this.isRmi = false;
        new Thread(clientSocketMiddleware).start();
    }

    /**
     * <p>initialize.</p>
     *
     * @throws java.rmi.RemoteException if any.
     */
    public void initialize() throws RemoteException {
        this.serverInterface.register(this);
    }


    /**
     * <p>Getter for the field <code>serverInterface</code>.</p>
     *
     * @return a {@link it.polimi.server.network.ServerInterface} object
     */
    public ServerInterface getServerInterface()
    {
        return this.serverInterface;
    }

    /**
     * <p>sendToServer.</p>
     *
     * @param string a {@link java.lang.String} object
     * @throws java.rmi.RemoteException if any.
     */
    public void sendToServer(String string) throws RemoteException
    {
        client.setState(State.WAITINGFORRESPONSE);
        serverInterface.sendAction(string);
    }

    /** {@inheritDoc} */
    @Override
    public void send(String string) throws RemoteException
    {
        //System.out.println("Ricevuto: " + string);
        Message msg = Parser.fromJson(string, Message.class);
        msg.handleMessage(this.client);
    }

    /**
     * <p>Getter for the field <code>game</code>.</p>
     *
     * @return a {@link it.polimi.server.model.Game} object
     * @throws java.rmi.RemoteException if any.
     */
    public Game getGame() throws RemoteException{
        return this.game;

    }

    /**
     * <p>Setter for the field <code>game</code>.</p>
     *
     * @param model a {@link it.polimi.server.model.Game} object
     * @throws java.rmi.RemoteException if any.
     */
    public void setGame(Game model) throws RemoteException{
        this.game = model;
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws IOException {
        if (isRmi)
        {
            UnicastRemoteObject.unexportObject(this, true);
        }
    }
}
