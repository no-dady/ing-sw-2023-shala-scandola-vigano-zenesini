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



public class ClientSkeleton implements ClientInterface, Runnable {
    private State currState = State.WAITINGFORRESPONSE;

    private final Socket socket;

    private final Server server;

    private String nickName;

    private Lobby lobby;

    private DataOutputStream out;

    private boolean isActive = false;


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
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            boolean confirm = false;
            isActive = true;
            server.register(this);

            receive(in);

            in.close();
            out.close();
            socket.close();
            //removeClientSkeletonThread
            System.out.println("Disconnected");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                close(nickName, lobby);
            } catch (RemoteException ignored) {}
        }
    }

    private void receive(DataInputStream in)
    {
        try
        {
            while (isActive) {
                int whatIsSending = in.readInt();
                String rec = in.readUTF();
                switch (whatIsSending) {
                    case 0:
                        server.sendMessage(rec);
                        break;

                    case 1:
                        server.sendSetupFirst(rec);
                        break;

                    case 2:
                        server.sendSetupAll(rec);
                        break;
                }
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {

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

    public boolean isActive() {
        return isActive;
    }

    public void closeConnection()
    {
        isActive = false;
    }

}