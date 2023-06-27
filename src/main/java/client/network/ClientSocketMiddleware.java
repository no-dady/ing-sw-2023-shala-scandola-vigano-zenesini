package client.network;

import client.Client;
import server.network.ServerInterface;
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

    private String ip;

    private int port;

    private DataInputStream in;
    private DataOutputStream out;

    private Socket socket;

    private ClientInterface clientinterface;

    public ClientSocketMiddleware(Client client, String ip, int port, ClientInterface clientInterface) throws IOException {
        this.client = client;
        this.ip = ip;
        this.port = port;
        this.clientinterface = clientInterface;

        socket = new Socket(ip, port);
    }

    @Override
    public void run()
    {
        try
        {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            String read;
            while (client.isActive()) {
                read = in.readUTF();
                clientinterface.send(read);
            }
        } catch(Exception e) {
            client.setActive(false);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e)
            {
                System.out.println("Cannot close the Socket connection");
            }
        }
    }

    @Override
    public void register(ClientInterface client) throws RemoteException
    {}

    @Override
    public void sendMessage(String json) throws RemoteException
    {
        try
        {
            out.writeInt(0);
            out.writeUTF(json);
            out.flush();
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send string Message from client", e);
        }
    }

    @Override
    public void sendSetupFirst(String json) throws RemoteException
    {
        try
        {
            out.writeInt(1);
            out.writeUTF(json);
            out.flush();
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send string SetupFirst from client", e);
        }
    }

    @Override
    public void sendSetupAll(String json) throws RemoteException
    {
        try
        {
            out.writeInt(2);
            out.writeUTF(json);
            out.flush();
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send string SetupAll from client", e);
        }
    }

    public void receive(ClientHandler clientHandler) throws RemoteException
    {
    }

    @Override
    public void close() throws IOException {
    }
}
