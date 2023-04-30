package org.gamein.network.SocketComm;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;
import org.gamein.network.RMIComm.RMIClientInterface;
import org.gamein.network.RMIComm.RMIServerInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

public class ClientSkeleton implements RMIClientInterface {
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    public ClientSkeleton(Socket socket) throws RemoteException
    {
        try
        {
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {
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

    //Action n 1
    public void sendChoice(int columnChoice) throws RemoteException
    {
        try
        {
            oos.writeInt(1);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send action number sendChoice", e);
        }

        try
        {
            oos.writeInt(columnChoice);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send column choice", e);
        }
    }

    //Action n 2
    public void sendPick(Tile[] tilePick) throws RemoteException
    {
        try
        {
            oos.writeInt(2);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send action number sendPick", e);
        }

        try
        {
            oos.writeObject(tilePick);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send tile pick from board", e);
        }
    }

    public void receive(RMIServerInterface server) throws RemoteException
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
                Tile[][] shelf;
                try {
                    shelf = (Tile[][]) ois.readObject();
                } catch (IOException e) {
                    throw new RemoteException("Cannot receive Shelf from client", e);
                } catch (ClassNotFoundException e) {
                    throw new RemoteException("Cannot deserialize Shelf from client", e);
                }
            }
            case 2 -> {
                Board board;
                try {
                    board = (Board) ois.readObject();
                } catch (IOException e) {
                    throw new RemoteException("Cannot receive Board from client", e);
                } catch (ClassNotFoundException e) {
                    throw new RemoteException("Cannot deserialize Board from client", e);
                }
            }
            case 3 -> {
                Bookshelf bookshelf;
                try {
                    bookshelf = (Bookshelf) ois.readObject();
                } catch (IOException e) {
                    throw new RemoteException("Cannot receive Bookshelf from client", e);
                } catch (ClassNotFoundException e) {
                    throw new RemoteException("Cannot deserialize Bookshelf from client", e);
                }
            }
        }
    }
}
