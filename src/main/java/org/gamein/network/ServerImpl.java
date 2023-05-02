package org.gamein.network;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;

import java.rmi.*;
import java.rmi.server.*;

/**
 * The type Server.
 */
public class ServerImpl extends UnicastRemoteObject implements Server {

    /**
     * The Client.
     */
//Temporary position for testing purpose
    public Client client;

    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Instantiates a new Server.
     *
     * @throws RemoteException the remote exception
     */
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
