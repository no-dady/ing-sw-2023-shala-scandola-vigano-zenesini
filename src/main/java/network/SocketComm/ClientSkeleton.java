package network.SocketComm;

import network.ClientInterface;
import network.ServerInterface;
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

            String input;
            while (true)
            {
                input = (String) ois.readObject();
                System.out.println("Received " + input);
                if (input.equalsIgnoreCase("quit"))
                {
                    break;
                }
            }

            ois.close();
            oos.close();
            socket.close();
            System.out.println("Disconnected");
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        } catch(ClassNotFoundException e)
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

    //Action n 4
    @Override
    public void testSend(String string) throws RemoteException
    {
        try
        {
            oos.writeInt(4);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send action number testSend", e);
        }

        try
        {
            oos.writeObject(string);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send the String", e);
        }
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
            String read = ois.readUTF();
            System.out.println("Received " + read);
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot receive from client", e);
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
