package org.gamein.network;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;

import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements Server {

    //Temporary position for testing purpose
    public Client client;

    public Client getClient() {
        return this.client;
    }

    public ServerImpl() throws RemoteException {
        super();
    }

    @Override
    public void register(Client client)
    {
        try {
            client.testSend("Test RMI string from server to client");
        } catch(RemoteException e) { System.out.println(e); }
    }

    @Override
    public void sendChoice(int columnChoice) throws RemoteException
    {

    }

    @Override
    public void sendPick(Tile[] tilePick) throws RemoteException
    {

    }

    @Override
    public void testSend(String string) throws RemoteException
    {
        System.out.println("Ricevuto: " + string);
    }
}
