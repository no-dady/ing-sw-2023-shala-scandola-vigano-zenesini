package client.network;

import client.Client;
import server.model.Game;
import server.network.ServerInterface;
import util.Messages.AskMoveMessage;
import util.Messages.Message;
import util.Parser;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * The type Server stub.
 */
//It implements the Server, but it will be used on the client-side to communicate
public class ClientSocketMiddleware implements ServerInterface, Runnable {
    private Client client;
    /**
     * The Ip.
     */
    private String ip;

    /**
     * The Port.
     */
    private int port;

    private ObjectOutputStream oos;
    private final DataInputStream ins;
    private DataOutputStream outs;
    private ObjectInputStream ois;

    private Socket socket;

    private ClientInterface clientinterface;
    /**
     * Instantiates a new Server stub.
     *
     * @param ip   the ip
     * @param port the port
     */
    public ClientSocketMiddleware(Client client, String ip, int port, ClientInterface clientInterface) throws IOException {
        this.client = client;
        this.ip = ip;
        this.port = port;
        this.clientinterface = clientInterface;

        socket = new Socket(ip, port);
        ins = new DataInputStream(socket.getInputStream());
        outs = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run()
    {
        try
        {
            // System.out.println("Created socket");
            // oos = new ObjectOutputStream(socket.getOutputStream());
            // System.out.println("Created oos");
            // ois = new ObjectInputStream(socket.getInputStream());
            // System.out.println("Created ois");

            String read;

            while (client.isActive()) {
                read = ins.readUTF();
                Message recv = Parser.fromJson(read, Message.class);
                recv.handleMessage(client);
                // int whatIsSending = (Integer) ois.readObject();
                // switch (whatIsSending) {
                //     case 0:
                //         String rec = ois.readObject().toString();
                //         clientinterface.send(rec);
                //         break;

                //     case 1:
                //         Game game = (Game) ois.readObject();
                //         clientinterface.setGame(game);
                //         break;
                // }
            }
        } catch(Exception e) {
            client.setActive(false);
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
    public void register(ClientInterface client) throws RemoteException
    {

    }

    @Override
    public void send(String json) throws RemoteException
    {
        try
        {
            outs.writeUTF(json);
            outs.flush();
            //oos.writeObject(string);
            //oos.flush();
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

    @Override
    public void close() throws IOException {
        this.ins.close();
        this.outs.close();
    }
}
