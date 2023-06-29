package it.polimi.server.network;

import it.polimi.client.network.ClientInterface;
import it.polimi.client.network.State;
import it.polimi.moves.Move;
import it.polimi.moves.MoveSelectColumn;
import it.polimi.moves.MoveSelectTiles;
import it.polimi.observer.Observable;
import it.polimi.observer.Observer;
import it.polimi.server.controller.GameController;
import it.polimi.server.controller.actions.ColumnSelectAction;
import it.polimi.server.controller.actions.TileSelectAction;
import it.polimi.server.model.*;
import it.polimi.server.network.SocketComm.ClientSkeleton;
import it.polimi.server.view.View;
import it.polimi.server.view.RemoteView;
import it.polimi.setup.SetupAll;
import it.polimi.setup.SetupFirst;
import it.polimi.util.Messages.*;

import it.polimi.util.Parser;

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
    private final ServerSocket serverSocket;
    private static Map<Integer, Lobby> lobbyList = new HashMap<Integer, Lobby>();
    private int lobbyIdProgressive = 0;
    private static List<Thread> memory = new ArrayList<Thread>();
    private boolean registeringClient = false;

    public static Queue<ClientInterface> getClientQueue() {
        return clientQueue;
    }

    private static Queue<ClientInterface> clientQueue = new LinkedList<>();
    private boolean isActive = true;

    private int portRmi;
    private int portSocket;
    public boolean isRMI;
    private ClientInterface tempClientInterface;

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
                System.out.println("Registering new it.polimi.client");
                Map.Entry<Integer, Lobby> lobbyFound = null;
                for (var lobby : lobbyList.entrySet())
                {
                    if (lobby.getValue().getLobbyStatus() == LobbyStatus.Setup)
                    {
                        if (!lobby.getValue().isFull())
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
                    Message msg = new StateMessage(State.SETUP);
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

    public static Lobby getLobby() {
        return lobbyList.get(lobbyList.size() - 1);
    }

    public void registrationFinished()
    {
        try {
            this.registeringClient = false;
            ClientInterface cli = clientQueue.remove();
            register(cli);
        } catch (Exception e) {
            System.out.println("Finished Registering queue");
        }
    }

    // RMI
    @Override
    public void sendMessage(String json) throws RemoteException {
        System.out.print("Ricevuto: " + json);
        Move movereceived = Parser.fromJson(json, Move.class);
        if (movereceived instanceof MoveSelectColumn){
            System.out.println("Ricevuto: " + movereceived.getLobbyId());
            lobbyList.get(movereceived.getLobbyId()).getController().update(new ColumnSelectAction((MoveSelectColumn) movereceived));
        }
        else if (movereceived instanceof MoveSelectTiles){
            System.out.println("Ricevuto: " + movereceived.getLobbyId());
            lobbyList.get(movereceived.getLobbyId()).getController().update(new TileSelectAction((MoveSelectTiles) movereceived));
        }
        notify(json);
    }

    @Override
    public void sendSetupFirst(String json) throws RemoteException {
        SetupFirst setupFirst = Parser.fromJson(json, SetupFirst.class);
        Lobby newLobby = new Lobby(setupFirst.getNumOfPlayers(), tempClientInterface, setupFirst.getParameter(), lobbyIdProgressive);
        lobbyList.put(lobbyIdProgressive, newLobby);
        lobbyIdProgressive++;
        registrationFinished();
    }

    @Override
    public void sendSetupAll(String json) throws RemoteException
    {
        System.out.println("SetupAll");
        SetupAll setupAll = Parser.fromJson(json, SetupAll.class);
        Lobby lobbyToJoin =  lobbyList.get(lobbyList.size() - 1);
        if (lobbyToJoin.checkNicknameAvailable(setupAll.getNickname()))
        {
            lobbyToJoin.addPlayer(tempClientInterface,setupAll.getNickname());
            registrationFinished();
        } else {
            StateMessage messageToSend = new StateMessage(State.SETUP);
            tempClientInterface.send(Parser.toJson(messageToSend, Message.class));
        }
        if (lobbyToJoin.isFull())
        {
            Thread lobbyThread = new Thread(lobbyToJoin);
            memory.add(lobbyThread);
            lobbyThread.start();
        }
    }


    @Override
    public void close() throws IOException {

    }


    public synchronized boolean findLobby(String name) {
        for(Lobby l : lobbyList.values()) {
            if(l.getLobbyName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public void removeLobby(Lobby lobby) {
        for (var entryLobby : lobbyList.entrySet())
        {
            if (lobby.equals(entryLobby.getValue()))
            {
                lobbyList.remove(entryLobby.getKey());
            }
        }
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
            System.out.println("[ RMI Info ] rmi://localhost:" + port + "/myShelfie");
            Naming.rebind("rmi://localhost:" + port + "/myShelfie", this);
            System.out.println("\nWaiting for messages..\n");
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    private void startSocket(int port) throws RemoteException {
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
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Server is running...");
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
