package org.gamein.network.SocketComm;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;
import org.gamein.network.RMIComm.RMIClientInterface;
import org.gamein.network.RMIComm.RMIServerInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class ServerStub implements RMIServerInterface {
    String ip;

    int port;

    private ObjectOutputStream oos;

    private ObjectInputStream ois;

    private Socket socket;

    public ServerStub(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
    }

    public void register(RMIClientInterface client) throws RemoteException
    {
        try
        {
            this.socket = new Socket(ip, port);
            try
            {
                this.oos = new ObjectOutputStream(socket.getOutputStream());
            }
            catch (IOException e)
            {
                throw new RemoteException("Cannot create output stream", e);
            }
            try
            {
                this.ois = new ObjectInputStream(socket.getInputStream());
            }
            catch (IOException e)
            {
                throw new RemoteException("Cannot create input stream", e);
            }
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot connect to the server", e);
        }
    }

    //Action n 1
    @Override
    public void sendShelf(Tile[][] shelf) throws RemoteException
    {
        try
        {
            oos.writeInt(1);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send action number sendShelf", e);
        }

        try
        {
            oos.writeObject(shelf);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send shelf", e);
        }
    }

    //Action n 2
    @Override
    public void sendBoard(Board board) throws RemoteException
    {
        try
        {
            oos.writeInt(2);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send action number sendBoard", e);
        }

        try
        {
            oos.writeObject(board);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send board", e);
        }
    }

    //Action n 3
    @Override
    public void sendBookshelf(Bookshelf bookshelf) throws RemoteException
    {
        try
        {
            oos.writeInt(3);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send action number sendBookshelf", e);
        }

        try
        {
            oos.writeObject(bookshelf);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send the bookshelf", e);
        }
    }

    public void receive(RMIClientInterface client) throws RemoteException
    {
        int actionNumber;

        try
        {
            actionNumber = ois.readInt();
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot receive actionNumber from client", e);
        }

        switch (actionNumber) {
            case 1 -> {
                int choice;
                try {
                    choice = ois.readInt();
                } catch (IOException e) {
                    throw new RemoteException("Cannot receive choice from client", e);
                }
            }
            case 2 -> {
                Tile[] tilePick;
                try {
                    tilePick = (Tile[]) ois.readObject();
                } catch (IOException e) {
                    throw new RemoteException("Cannot receive tilePick from client", e);
                } catch (ClassNotFoundException e) {
                    throw new RemoteException("Cannot deserialize tilePick from client", e);
                }
            }
        }
    }
}
