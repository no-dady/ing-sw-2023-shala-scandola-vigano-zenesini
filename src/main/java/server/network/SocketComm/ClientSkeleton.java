package server.network.SocketComm;

import client.UI;
import client.network.ClientInterface;
import server.model.Game;
import server.network.ServerInterface;
import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * The type Client skeleton.
 */
//It implements the client, but it will be used on the server-side to call functions
public class ClientSkeleton implements ClientInterface, Runnable {
    private ObjectOutputStream oos;

    private final Socket socket;

    private ServerInterface serverInterface;

    private String nickName;

    /**
     * Instantiates a new Client skeleton.
     *
     * @param socket the socket
     * @throws RemoteException the remote exception
     */
    public ClientSkeleton(Socket socket, ServerInterface serverInterface) throws RemoteException
    {
        this.socket = socket;
        this.serverInterface = serverInterface;
        this.nickName = "";
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
            serverInterface.register(this);

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
                serverInterface.send(rec);
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
}
