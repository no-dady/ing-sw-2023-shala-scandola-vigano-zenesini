package network.SocketComm;

import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;
import server.network.Client;
import server.network.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * The type Server stub.
 */
//It implements the Server, but it will be used on the client-side to communicate
public class ServerStub implements Server {
    /**
     * The Ip.
     */
    String ip;

    /**
     * The Port.
     */
    int port;

    private ObjectOutputStream oos;

    private ObjectInputStream ois;

    private Socket socket;

    /**
     * Instantiates a new Server stub.
     *
     * @param ip   the ip
     * @param port the port
     */
    public ServerStub(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void register(Client client) throws RemoteException
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

    /**
     * Close.
     *
     * @throws RemoteException the remote exception
     */
    public void close() throws RemoteException {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RemoteException("Cannot close socket", e);
        }
    }

    //Action n 1
    @Override
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
    @Override
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

    //Action n 3
    @Override
    public void testSend(String string) throws RemoteException
    {
        try
        {
            oos.writeInt(3);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send action number sendTest", e);
        }

        try
        {
            oos.writeObject(string);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send string from client", e);
        }
    }

    /**
     * Receive.
     *
     * @param client the client
     * @throws RemoteException the remote exception
     */
    public void receive(Client client) throws RemoteException
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
                client.sendShelf(shelf);
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
                client.sendBoard(board);
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
                client.sendBookshelf(bookshelf);
            }
            case 4 -> {
                String string;
                try {
                    string = (String) ois.readObject();
                } catch (IOException e) {
                    throw new RemoteException("Cannot receive String from client", e);
                } catch (ClassNotFoundException e) {
                    throw new RemoteException("Cannot deserialize String from client", e);
                }
                client.testSend(string);
            }
        }
    }
}
