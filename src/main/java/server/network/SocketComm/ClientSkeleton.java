package server.network.SocketComm;

import client.UI;
import client.network.ClientInterface;
import observer.Observer;
import client.network.State;
import server.model.Game;
import server.model.Lobby;
import server.network.Server;
import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;



public class ClientSkeleton implements ClientInterface, Runnable {
    private ObjectOutputStream oos;
    private State currState = State.WaitingForResponse;

    private final Socket socket;

    private final Server server;

    private String nickName;

    private Lobby lobby;

    /**
     * Instantiates a new Client skeleton.
     *
     * @param socket the socket
     * @throws RemoteException the remote exception
     */
    public ClientSkeleton(Socket socket, Server server) throws RemoteException
    {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run()
    {
        System.out.println("Start thread");
        try
        {
            ObjectInputStream ois;
            System.out.println("Creating streams");
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Created Output");
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Created input");
            server.register(this);

            receive(ois);

            ois.close();
            oos.close();
            socket.close();
            System.out.println("Disconnected");
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void notifyNewConn(String nickname)
    {
        try
        {
            oos.writeObject(nickname + " connected");
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    //Action n 4
    @Override
    public void send(String string) throws RemoteException
    {
        try
        {
            oos.writeObject(string);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send the String", e);
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
    public void setGame(Game model) {

    }

    @Override
    public void setState(State state) {
        this.currState = state;
    }

    @Override
    public State getState() throws RemoteException {
        return null;
    }

    public String getNickname()
    {
        return this.nickName;
    }

    /**
     * Receive.
     *
     * @throws RemoteException the remote exception
     */
    public void receive(ObjectInputStream ois) throws RemoteException
    {
        String rec;
        while (true)
        {
            try
            {
                rec = (String) ois.readObject();
                server.send(rec);
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
        }
        //int actionNumber;
//
        //try
        //{
        //    actionNumber = ois.readInt();
        //}
        //catch (IOException e)
        //{
        //    throw new RemoteException("Cannot receive actionNumber from client", e);
        //}
//
        //switch (actionNumber) {
        //    case 1 -> {
        //        int choice;
        //        try {
        //            choice = ois.readInt();
        //        } catch (IOException e) {
        //            throw new RemoteException("Cannot receive choice from client", e);
        //        }
        //        serverInterface.sendChoice(choice);
        //    }
        //    case 2 -> {
        //        Tile[] tilePick;
        //        try {
        //            tilePick = (Tile[]) ois.readObject();
        //        } catch (IOException e) {
        //            throw new RemoteException("Cannot receive tilePick from client", e);
        //        } catch (ClassNotFoundException e) {
        //            throw new RemoteException("Cannot deserialize tilePick from client", e);
        //        }
        //        serverInterface.sendPick(tilePick);
        //    }
        //    case 3 -> {
        //        String string;
        //        try {
        //            string = (String) ois.readObject();
        //        } catch (IOException e) {
        //            throw new RemoteException("Cannot receive String from client", e);
        //        } catch (ClassNotFoundException e) {
        //            throw new RemoteException("Cannot deserialize String from client", e);
        //        }
        //        serverInterface.testSend(string);
        //    }
        //    case 4 -> {
        //        String nickname;
        //        try {
        //            nickname = (String) ois.readObject();
        //        } catch (IOException e) {
        //            throw new RemoteException("Cannot receive nickname from client", e);
        //        } catch (ClassNotFoundException e) {
        //            throw new RemoteException("Cannot deserialize nickname from client", e);
        //        }
        //        serverInterface.register(this, nickname);
        //    }
        //}
    }


    private void close(String playerName, Lobby lobby) throws RemoteException {
        System.out.println("Deregistering Client");
        if(lobby != null && server.findLobby(lobby.getLobbyName())) {
            if(lobby.disconnectPlayer(playerName)) {
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
            for(Observer<String> observer : observers){
                observer.update(message);
            }
        }
    }
}
