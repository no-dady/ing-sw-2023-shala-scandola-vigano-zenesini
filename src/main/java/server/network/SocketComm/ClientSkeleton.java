package server.network.SocketComm;

import client.UI;
import client.network.ClientInterface;
import observer.Observable;
import observer.Observer;
import client.network.State;
import server.model.Game;
import server.model.Lobby;
import server.network.Server;
import setup.Setup;
import setup.SetupFirst;
import util.Messages.AskSetupMessage;
import util.Messages.Message;
import util.Messages.NicknameMessage;
import util.Messages.StateMessage;
import util.Parser;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;



public class ClientSkeleton implements Observable<String>, ClientInterface, Runnable {
    private State currState = State.WAITINGFORRESPONSE;

    private final Socket socket;

    private final Server server;

    private String nickName;

    private Lobby lobby;

    private DataInputStream in;
    private DataOutputStream out;

    private boolean active = true;


    public ClientSkeleton(Socket socket, Server server) throws RemoteException {
        this.socket = socket;
        this.server = server;
    }

    private boolean recNickname(Setup setup) {
        return setup.getParameter() != null;
    }

    @Override
    public void send(String json) throws RemoteException {
        try {
            out.writeUTF(json);
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    void register() {
        try {
            Message msg = new AskSetupMessage();
            this.send(Parser.toJson(msg, Message.class));
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("Start thread");
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            boolean confirm = false;
            //register();
            String read;
            do {
                server.register(this);
                read = in.readUTF();
                System.out.println("Received: " + read);
                Setup setupper = Parser.fromJson(read, Setup.class);
                confirm = handleSetupper(setupper);
            } while(!confirm);

            while(isActive()) {
                read = in.readUTF();
                notify(read);
            }
            System.out.println("Disconnected");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                close(nickName, lobby);
            } catch (RemoteException ignored) {}
        }
    }

    public void notifyNewConn(String nickname) {
        try {
            out.writeUTF(nickname + " connected");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    @Override
    public void setGame(Game game) throws RemoteException {
        try {
            out.writeUTF("SSS");
        } catch (IOException e) {
            throw new RemoteException("Cannot send the Game", e);
        }
    }

    @Override
    public UI getUI() {
        return null;
    }

    @Override
    public Game getGame() {
        return null;
    }

    @Override
    public State getState() throws RemoteException {
        return null;
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public Lobby getLobby() {
        return this.lobby;
    }

    public String getNickname() {
        return this.nickName;
    }

    private void close(String playerName, Lobby lobby) throws RemoteException {
        System.out.println("Deregistering Client");
        if (lobby != null && server.findLobby(lobby.getLobbyName())) {
            if (lobby.disconnectPlayer(playerName)) {
                server.removeLobby(lobby);
            }
        }

        System.out.println("Done!");
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

    @Override
    public boolean handleSetupper(Setup setupper) throws RemoteException {
        if(this.lobby == null || (setupper.getParameter() != null && lobby.checkNicknameAvailable(setupper.getParameter()))) {
            System.out.println("Handle Setupper ::: " + setupper.getParameter());
            // if(setupper instanceof SetupFirst) {
            //     Server.addLobby(new Lobby(((SetupFirst) setupper).getNumOfPlayers(), this, setupper.getParameter()));
            // }
            // this.lobby = Server.getLobby();
            this.nickName = setupper.getParameter();
            //Server.addClientQueue(this);
            send(Parser.toJson(new StateMessage(State.INQUEUE), Message.class));
            return true;
        }
        return false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}