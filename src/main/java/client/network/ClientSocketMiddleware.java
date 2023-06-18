package client.network;

import client.Client;
import server.model.Tile;
import server.network.ServerInterface;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The type Server stub.
 */
//It implements the Server, but it will be used on the client-side to communicate
public class ClientSocketMiddleware implements ServerInterface, Runnable {
    Client client;
    /**
     * The Ip.
     */
    String ip;

    /**
     * The Port.
     */
    int port;

    private ObjectOutputStream oos;

    private Socket socket;

    private ClientInterface clientinterface;
    /**
     * Instantiates a new Server stub.
     *
     * @param ip   the ip
     * @param port the port
     */
    public ClientSocketMiddleware(Client client, String ip, int port, ClientInterface clientInterface)
    {
        this.client = client;
        this.ip = ip;
        this.port = port;
        this.clientinterface = clientInterface;
    }

    @Override
    public void run()
    {

        try
        {
            socket = new Socket(ip, port);
            System.out.println("Created socket");
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Created oos");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Created ois");

            System.out.println("Created streams");
            String rec;
            while (true)
            {
                rec = (String) ois.readObject();
                clientinterface.send(rec);
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void testContinousSend()
    {
        try
        {
            String input;
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while(true)
            {
                input = userInput.readLine();
                System.out.println("Sending " + input);
                oos.writeObject(input);
                if (input.equalsIgnoreCase("quit"))
                {
                    break;
                }
            }
        } catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void register(ClientInterface clientInterface) throws RemoteException
    {

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
    public void send(String string) throws RemoteException
    {
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
     * @param clientHandler the client
     * @throws RemoteException the remote exception
     */
    public void receive(ClientHandler clientHandler) throws RemoteException
    {
        //int actionNumber;

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
        //        Tile[][] shelf;
        //        try {
        //            shelf = (Tile[][]) ois.readObject();
        //        } catch (IOException e) {
        //            throw new RemoteException("Cannot receive Shelf from client", e);
        //        } catch (ClassNotFoundException e) {
        //            throw new RemoteException("Cannot deserialize Shelf from client", e);
        //        }
        //        client.sendShelf(shelf);
        //    }
        //    case 2 -> {
        //        Board board;
        //        try {
        //            board = (Board) ois.readObject();
        //        } catch (IOException e) {
        //            throw new RemoteException("Cannot receive Board from client", e);
        //        } catch (ClassNotFoundException e) {
        //            throw new RemoteException("Cannot deserialize Board from client", e);
        //        }
        //        client.sendBoard(board);
        //    }
        //    case 3 -> {
        //        Bookshelf bookshelf;
        //        try {
        //            bookshelf = (Bookshelf) ois.readObject();
        //        } catch (IOException e) {
        //            throw new RemoteException("Cannot receive Bookshelf from client", e);
        //        } catch (ClassNotFoundException e) {
        //            throw new RemoteException("Cannot deserialize Bookshelf from client", e);
        //        }
        //        client.sendBookshelf(bookshelf);
        //    }
        //    case 4 -> {
        //        String string;
        //        try {
        //            string = (String) ois.readObject();
        //        } catch (IOException e) {
        //            throw new RemoteException("Cannot receive String from client", e);
        //        } catch (ClassNotFoundException e) {
        //            throw new RemoteException("Cannot deserialize String from client", e);
        //        }
        //        client.testSend(string);
        //    }
        //}
    }
}
