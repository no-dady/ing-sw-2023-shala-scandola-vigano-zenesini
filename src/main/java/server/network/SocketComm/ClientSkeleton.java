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
    private State currState = State.WAITINGFORRESPONSE;

    private final Socket socket;

    private final Server server;

    private final int clientSkeletonId;
    private String nickName;

    private Lobby lobby;

    private boolean isActive;

    /**
     * Instantiates a new Client skeleton.
     *
     * @param socket the socket
     * @throws RemoteException the remote exception
     */
    public ClientSkeleton(Socket socket, Server server, int clientSkeletonId) throws RemoteException
    {
        this.socket = socket;
        this.server = server;
        this.clientSkeletonId = clientSkeletonId;
        this.isActive = true;
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
            server.removeClientSkeletonThread(clientSkeletonId);
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
            oos.writeObject(0);
            oos.writeObject(string);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send the String", e);
        }
    }

    @Override
    public void setGame(Game game) throws RemoteException
    {
        try
        {
            oos.writeObject(1);
            oos.writeObject(game);
        }
        catch (IOException e)
        {
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
        try
        {
            while (isActive) {
                int whatIsSending = (Integer) ois.readObject();
                switch (whatIsSending) {
                    case 0:
                        String rec = (String) ois.readObject();
                        server.send(rec);
                        break;

                    case 1:
                        closeConnection();
                        break;
                }
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection()
    {
        isActive = false;
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
