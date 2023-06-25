package server.network;

import client.network.ClientInterface;
import server.controller.GameController;
import server.model.Game;
import server.model.Lobby;
import server.model.LobbyStatus;
import server.model.Player;
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
import java.util.*;

/**
 * The type Server.
 */
public class Server extends UnicastRemoteObject implements ServerInterface, Runnable {

    /**
     * The Client.
     */
//Temporary position for testing purpose
    public Map<String, ClientInterface> clientList = new HashMap<>();
    private final ServerSocket serverSocket;
    private static List<Lobby> lobbyList = new ArrayList<>();

    private boolean isActive = true;

    private int portRmi;
    private int portSocket;
    public boolean isRMI;
    private ClientInterface tempClientInterface;

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

    public Server() throws IOException, RemoteException {
        super();
        this.portRmi = 1900;
        this.portSocket = 1337;
        this.serverSocket = new ServerSocket(portSocket);
    }


    public Server(int portRmi, int portSocket) throws IOException {
        super();
        this.portRmi = portRmi;
        this.portSocket = portSocket;
        this.serverSocket = new ServerSocket(portSocket);
    }

    /**
     * Instantiates a new Server.
     *
     * @throws RemoteException the remote exception
     */
    // public Server(boolean isRMI) throws RemoteException {
    //     super();
    //     this.isRMI = isRMI;
    //     this.clientList = new HashMap<String, ClientInterface>();
    // }

    @Override
    public void register(ClientInterface client)
    {
        /*
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
        this.tempClientInterface = client;
        Message msg = new AskMoveMessage(0);
        try {
            client.send(Parser.toJson(msg, AskMoveMessage.class));
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
        // AskMoveMessage messageToSend = new AskMoveMessage(0);
        // try
        // {
        //     client.send(Parser.toJson(messageToSend, AskMoveMessage.class));
        // } catch (RemoteException e)
        // {
        //     System.out.println("Cannot send AskMoveMessage from server");
        // }

         */
    }

    @Override
    public void send(String json) throws RemoteException {
    }


    /*
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
            ClientInterface client = this.tempClientInterface;
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
                client.send(Parser.toJson(messageToSend, AskMoveMessage.class));
            }
            else
            {
                if (!lobbyFound.checkNicknameAvailable(nickName))
                {
                    lobbyFound.addPlayer(client, nickName);
                }
                else
                {
                    System.out.println(nickName + " was already taken");
                    ErrorMessage message = new ErrorMessage("Nickname already taken");
                    client.send(Parser.toJson(message, ErrorMessage.class));
                }
            }
        } else //if(messageReceived instanceof CreateLobbyMessage trueMessageReceived)
        {
            CreateLobbyMessage trueMessageReceived = Parser.fromJson(string, CreateLobbyMessage.class);
            Lobby newLobby = new Lobby(trueMessageReceived.getNumberOfPlayer(), tempClientInterface, trueMessageReceived.getNickName());
            lobbyList.add(newLobby);
            ConfirmMessage messageToSend = new ConfirmMessage("Joined Lobby as Admin", 0);
            tempClientInterface.send(Parser.toJson(messageToSend, ConfirmMessage.class));
            tempClientInterface = null;
        }

    }
    */
    @Override
    public void close() throws IOException {

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

    private void addObserverGame(ArrayList<View> playersView, Game game, GameController controller){
        for(View view: playersView) {
            instanceView(view, game, controller);
        }
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
            LocateRegistry.createRegistry(port);

            Naming.rebind("rmi://localhost:" + port + "/myShelfie", this);
            ClientInterface client = null;
            while (client == null)
            {
                client = this.getClient();
            }
            client.send("Test RMI string from server to client");
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    private void startSocket(int port) throws RemoteException {
        // ArrayList<Thread> memory = new ArrayList<Thread>();
        // ArrayList<ClientSkeleton> clientsList = new ArrayList<ClientSkeleton>();
        // Server serverInterface = new Server(false);
        // System.out.println("Server Started");

        // try (ServerSocket serverSocket = new ServerSocket(port)) {
        //     while (true) {
        //         System.out.println("Waiting connections...");
        //         Socket socket = serverSocket.accept();
        //         System.out.println("New connection found");
        //         ClientSkeleton clientSocketMiddleware = new ClientSkeleton(socket, serverInterface);
        //         //To send the info you have to call the clientSkeleton's function on the server-side
        //         //clientsList.add(clientSkeleton);
        //         Thread clientSkeletonThread = new Thread(clientSocketMiddleware);
        //         memory.add(clientSkeletonThread);
        //         clientSkeletonThread.start();
        //         System.out.println("Thread launched from main");
        //     }
        // }
        // catch (IOException e)
        // {
        //     System.err.println("Cannot create server socket:\n" + e.getMessage());
        // }

        ArrayList<ClientSkeleton> clientsList = new ArrayList<ClientSkeleton>();
        System.out.println("Server is running...");
        System.out.println("Server socket info:");
        System.out.println("\t port: " + serverSocket.getLocalPort());
        System.out.println("\t address: " + serverSocket.getInetAddress());
        System.out.println("\nWaiting for messages..\n");
        while(true){
            try {
                Socket newSocket = serverSocket.accept();
                ClientSkeleton socketConnection = new ClientSkeleton(newSocket, this);
                new Thread(socketConnection).start();
            } catch (IOException e) {
                System.out.println("Connection Error!");
            } finally {
            }
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
                    System.out.println("[ERROR] Something bad happened in socket thread");
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
