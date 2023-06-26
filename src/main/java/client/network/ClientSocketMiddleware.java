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

    private final DataInputStream ins;
    private DataOutputStream outs;

    private Socket socket;

    private ClientInterface clientinterface;

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
            String read;
            while (client.isActive()) {
                read = ins.readUTF();
                Message recv = Parser.fromJson(read, Message.class);
                recv.handleMessage(client);
            }
        } catch(Exception e) {
            client.setActive(false);
            e.printStackTrace();
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
            outs.writeUTF(json);
            outs.flush();
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send string from client", e);
        }
    }

    @Override
    public void sendSetupFirst(String json) throws RemoteException
    {
        sendMessage(json);
    }

    @Override
    public void sendSetupAll(String json) throws RemoteException
    {
        sendMessage(json);
    }

    public void receive(ClientHandler clientHandler) throws RemoteException
    {
    }

    @Override
    public void close() throws IOException {
        this.ins.close();
        this.outs.close();
    }
}
