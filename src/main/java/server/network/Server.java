package server.network;

import client.network.ClientInterface;
import server.controller.GameController;
import server.model.Game;
import server.model.Lobby;
import server.model.LobbyStatus;
import server.model.Player;
import server.model.Tile;
import server.network.SocketComm.ClientSkeleton;
import server.view.View;
import server.view.RemoteView;
import util.Messages.*;

import util.Parser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Server.
 */
public class Server extends UnicastRemoteObject implements ServerInterface, Runnable {

    /**
     * The Client.
     */
//Temporary position for testing purpose
    public Map<String, ClientInterface> clientList;

    private static List<Lobby> lobbyList = new ArrayList<>();

    private int portRmi;
    private int portSocket;
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


    public Server(int portRmi, int portSocket) throws RemoteException {
        this.portRmi = portRmi;
        this.portSocket = portSocket;
    }

    /**
     * Instantiates a new Server.
     *
     * @throws RemoteException the remote exception
     */
    public Server(boolean isRMI) throws RemoteException {
        super();
        this.isRMI = isRMI;
        this.clientList = new HashMap<String, ClientInterface>();
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
            ConfirmMessage messageToSend = new ConfirmMessage("Joined Lobby as Admin", 0);
            tempClient.send(Parser.toJson(messageToSend, ConfirmMessage.class));
            tempClient = null;
        }

    }


    public synchronized boolean findLobby(String name) {
        for(var l : lobbyList) {
            if(l.getLobbyName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public void removeLobby(Lobby lobby) {
        lobbyList.removeIf(l -> l.getLobbyName().equals(lobby.getLobbyName()));
    }

    public static void instanceView(View view, Game game, GameController controller) {
        game.getBoard().addObserver(view);
        for(var cgc : game.getCgcs()) {
            cgc.addObserver(view);
        }
        game.addObserver(view);

        for(Player p : game.getPlayers()) {
            p.getBookshelf().addObserver(view);
            p.getPersonalGoalCard().addObserver(view);
            p.addObserver(view);
        }

        view.addObserver(controller);
    }

    private HashMap<String,View> instanceViews(Map<String, ClientInterface> waitingConnection, ArrayList<Player> players) throws RemoteException {
        HashMap <String, View> playersView = new HashMap<>();
        for(Player player: players){
            playersView.put(player.getUserName(), new RemoteView(player, waitingConnection.get(player.getUserName())));
        }
        return playersView;
    }


    private void startRMI(int port)
    {
        try
        {
            Server obj = new Server(true);

            LocateRegistry.createRegistry(port);

            Naming.rebind("rmi://localhost:" + port + "/myShelfie", obj);
            ClientInterface clientInterface = null;
            while (clientInterface == null)
            {
                clientInterface = obj.getClient();
            }
            clientInterface.send("Test RMI string from server to client");
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    private static void startSocket(int port) throws RemoteException {
        ArrayList<Thread> memory = new ArrayList<Thread>();
        ArrayList<ClientSkeleton> clientsList = new ArrayList<ClientSkeleton>();
        Server serverInterface = new Server(false);
        System.out.println("Server Started");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Waiting connections...");
                Socket socket = serverSocket.accept();
                System.out.println("New connection found");
                ClientSkeleton clientSocketMiddleware = new ClientSkeleton(socket, serverInterface);
                //To send the info you have to call the clientSkeleton's function on the server-side
                //clientsList.add(clientSkeleton);
                Thread clientSkeletonThread = new Thread(clientSocketMiddleware);
                memory.add(clientSkeletonThread);
                clientSkeletonThread.start();
                System.out.println("Thread launched from main");
            }
        }
        catch (IOException e)
        {
            System.err.println("Cannot create server socket:\n" + e.getMessage());
        }
    }

    @Override
    public void run() {
        Thread rmi = new Thread() {
            @Override
            public void run() {
                startRMI(portRmi);
            }
        };

        Thread sockt = new Thread() {
            @Override
            public void run() {
                try {
                    startSocket(portSocket);
                } catch (RemoteException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        };

        rmi.start();
        sockt.start();

        try {
            rmi.join();
            sockt.join();
        } catch (InterruptedException e)
        {
            System.out.println("No connection protocol available");
        }
    }

}
