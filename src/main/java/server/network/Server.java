package server.network;

import client.network.ClientInterface;
import server.model.Lobby;
import server.model.LobbyStatus;
import server.model.Tile;
import util.Messages.*;

import util.Parser;

import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.List;
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

    private List<Lobby> lobbyList;

    public boolean isRMI;

    private ClientInterface tempClient;

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
    public Server(boolean isRMI, List<Lobby> lobbyList) throws RemoteException {
        super();
        this.isRMI = isRMI;
        this.clientList = new HashMap<String, ClientInterface>();
        this.lobbyList = lobbyList;
    }

    @Override
    public void register(ClientInterface clientInterface)
    {
        //clientList.put(nickName, clientInterface);
        //System.out.println(nickName + " joined the match");
        System.out.println("Registering new client");
        //if (isRMI)
        //{
        //    try {
        //        for (Map.Entry<String, ClientInterface> entry : clientList.entrySet())
        //        {
        //            entry.getValue().send("Sto avvisando " + entry.getKey() + " che si é aggiunto alla partita " + nickName);
        //        }
        //    } catch(RemoteException e) { System.out.println(e); }
        //}
        this.tempClient = clientInterface;
        AskMoveMessage messageToSend = new AskMoveMessage(0);
        try
        {
            clientInterface.send(Parser.toJson(messageToSend, AskMoveMessage.class));
        } catch (RemoteException e)
        {
            System.out.println("Cannot send AskMoveMessage from server");
        }
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
    public void send(String string) throws RemoteException
    {
        System.out.println("Ricevuto: " + string);
        //PARSE THE STRING INTO A MESSAGE AND DO AS THE MESSAGE SAY
        //Message messageReceived = Parser.parseFromJsonString(string, NicknameMessage.class);
        //if(messageReceived instanceof NicknameMessage trueMessageReceived)]#
        if(!string.contains("numberOfPlayer"))
        {
            NicknameMessage trueMessageReceived = Parser.fromJson(string, NicknameMessage.class);
            System.out.println("Nickname received");
            Lobby lobbyFound = null;
            String nickName = trueMessageReceived.getNickName();
            ClientInterface clientInterface = this.tempClient;
            for (Lobby lobby : lobbyList)
            {
                if (lobby.getLobbyStatus() == LobbyStatus.Setup)
                {
                    if (!lobby.isFull())
                    {
                        lobbyFound = lobby;
                    }
                }
            }
            if (lobbyFound == null)
            {
                AskMoveMessage messageToSend = new AskMoveMessage(1);
                clientInterface.send(Parser.toJson(messageToSend, AskMoveMessage.class));
            }
            else
            {
                if (!lobbyFound.checkNicknameAvailable(nickName))
                {
                    lobbyFound.addPlayer(clientInterface, nickName);
                }
                else
                {
                    System.out.println(nickName + " was already taken");
                    ErrorMessage message = new ErrorMessage("Nickname already taken");
                    clientInterface.send(Parser.toJson(message, ErrorMessage.class));
                }
            }
        } else //if(messageReceived instanceof CreateLobbyMessage trueMessageReceived)
        {
            CreateLobbyMessage trueMessageReceived = Parser.fromJson(string, CreateLobbyMessage.class);
            Lobby newLobby = new Lobby(trueMessageReceived.getNumberOfPlayer(), tempClient, trueMessageReceived.getNickName());
            lobbyList.add(newLobby);
            ConfirmMessage messageToSend = new ConfirmMessage("Joined Lobby as Admin");
            tempClient.send(Parser.toJson(messageToSend, ConfirmMessage.class));
            tempClient = null;
        }

    }
}
