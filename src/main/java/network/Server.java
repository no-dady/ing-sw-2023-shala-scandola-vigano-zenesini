package network;

import server.model.Tile;

import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Server.
 */
public class Server extends UnicastRemoteObject implements ServerInterface {

    /**
     * The Client.
     */
//Temporary position for testing purpose
    public Map<String, ClientInterface> clientList;

    /**
     * Gets client.
     *
     * @return the client
     */
    public ClientInterface getClient() {
        if (clientList.size() > 0)
        {
            return this.clientList.get(0);
        }
        return null;
    }

    /**
     * Instantiates a new Server.
     *
     * @throws RemoteException the remote exception
     */
    public Server() throws RemoteException {
        super();
        this.clientList = new HashMap<String, ClientInterface>();
    }

    @Override
    public void register(ClientInterface clientInterface, String nickName)
    {
        try {
            clientInterface.testSend("Test RMI string from server to client");
            clientList.put(nickName, clientInterface);
            System.out.println(nickName + " joined the match");
            System.out.println("Client collegati: " + clientList.size());
            for (Map.Entry<String, ClientInterface> entry : clientList.entrySet())
            {
                entry.getValue().testSend("Sto avvisando " + entry.getKey() + " che si é aggiunto alla partita " + nickName);
            }
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
