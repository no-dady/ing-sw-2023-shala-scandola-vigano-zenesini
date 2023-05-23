package client.network;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.Client;
import network.Server;
import server.controller.actions.Action;
import util.Parser;

public class RMIHandler extends UnicastRemoteObject implements Connection {
    Server server;
    Client client;
    public RMIHandler (Client client, String ip, String port) throws RemoteException, MalformedURLException, NotBoundException {
        super();
        this.client = client;
        this.server = (Server) Naming.lookup(String.format("rmi://{0}:{1}/myShelfie", ip, port));
        this.server.register(this.client);
    }
    @Override
    public void sendSetupper() {}
    @Override
    public void closeConnection() {}
    @Override
    public void sendMove(Action action) {
        try {
            server.testSend(Parser.parseAction(action));
        } catch (RemoteException ex) {
            return;
        }
    }
    @Override
    public boolean setOnline(Client client) { return false; }
    @Override
    public boolean isOnline() { return false; }

    String getMessage() throws RemoteException { return new String(); }

}
