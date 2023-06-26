package server.network;

import client.network.ClientInterface;
import client.network.State;
import observer.Observable;
import observer.Observer;
import org.javatuples.Pair;
import server.controller.GameController;
import server.controller.actions.Action;
import server.model.*;
import server.network.SocketComm.ClientSkeleton;
import server.view.View;
import server.view.RemoteView;
import setup.SetupAll;
import setup.SetupFirst;
import util.Messages.*;

import util.Parser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.*;

public class Server extends UnicastRemoteObject implements Observable<String>, ServerInterface, Runnable {

    /**
     * The Client.
     */
    public Map<String, ClientInterface> clientList = new HashMap<>();
    private final ServerSocket serverSocket;
    private static List<Lobby> lobbyList = new ArrayList<>();
    private boolean registeringClient = false;
    private List<ClientInterface> clientQueue = new ArrayList<>();
    private boolean isActive = true;

    private int portRmi;
    private int portSocket;
    public boolean isRMI;
    private ClientInterface tempClientInterface;

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

    // RMI
    @Override
    public void register(ClientInterface client)
    {
        if (!registeringClient)
        {
            try {
                registeringClient = true;
                tempClientInterface = client;
                System.out.println("Registering new client");
                Lobby lobbyFound = null;
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
                    Message msg = new StateMessage(State.SETUPFIRST);
                    client.send(Parser.toJson(msg, Message.class));
                } else {
                    Message msg = new StateMessage(State.SETTINGNICKNAME);
                    client.send(Parser.toJson(msg, Message.class));
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            clientQueue.add(client);
            try {
                Message msg = new StateMessage(State.INQUEUE);
                client.send(Parser.toJson(msg, Message.class));
            } catch (RemoteException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public static void addLobby(Lobby lobby) {
        lobbyList.add(lobby);
    }

    public static Lobby getLobby() {
        return lobbyList.get(lobbyList.size() - 1);
    }

    public void registrationFinished()
    {
        if (clientQueue.size() > 0)
        {
            ClientInterface cli = clientQueue.remove(0);
            this.registeringClient = false;
            register(cli);
        }
        else {
            this.registeringClient = false;
            System.out.println("Finished");
        }
    }

    // RMI
    @Override
    public void sendMessage(String json) throws RemoteException {
        System.out.print("Ricevuto: " + json);
        notify(json);
    }

    @Override
    public void sendSetupFirst(String json) throws RemoteException {
        SetupFirst setupFirst = Parser.fromJson(json, SetupFirst.class);
        Lobby newLobby = new Lobby(setupFirst.getNumOfPlayers(), tempClientInterface, setupFirst.getName());
        System.out.println("Qui");
        lobbyList.add(newLobby);
        System.out.println("Quo");
        registrationFinished();
    }

    @Override
    public void sendSetupAll(String json) throws RemoteException
    {
        SetupAll setupAll = Parser.fromJson(json, SetupAll.class);
        lobbyList.get(lobbyList.size() - 1).addPlayer(tempClientInterface, setupAll.getNickname());
        registrationFinished();
    }


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
        for(CommonGoalCardStrategy c : game.getCgcs()) {
            c.addObserver(view);
        }
        game.addObserver(view);

        for(Player p : game.getPlayers()) {
            p.getBookshelf().addObserver(view);
            p.getPersonalGoalCard().addObserver(view);
            p.addObserver(view);
        }

        view.addObserver(controller);
        controller.addObserver(game);
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
            // ClientInterface client = null;
            // while (client == null)
            // {
            //     client = this.getClient();
            // }
            // client.send("Test RMI string from server to client");
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    private void startSocket(int port) throws RemoteException {
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

    public synchronized boolean isActive() {
        return isActive;
    }

    public synchronized void setActive(boolean active) {
        isActive = active;
    }

    private transient final List<Observer<String>> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer<String> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    @Override
    public void notify(String message) {
        synchronized (observers) {
            for (Observer<String> observer : observers) {
                observer.update(message);
            }
        }
    }
}
