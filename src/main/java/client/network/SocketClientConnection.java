package client.network;

import client.Client;
import moves.Move;
import network.Messages.Message;
import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;
import util.Parser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;

public class SocketClientConnection implements ClientInterface {

    private final String ip;
    private final int port;
    private Client client;

    private DataOutputStream socketOut;
    private DataInputStream socketIn;
    private Socket socket;
    private Thread readingThread;

    public SocketClientConnection(Client client, String ip, int port) {
        this.client = client;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void init() {


    }

    public Thread asyncReadFromSocket(final DataInputStream socketIn) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String read;
                    while(client.isActive()) {
                        read = socketIn.readUTF();
                        System.out.println(read);
                        Message received = Parser.parseFromJson(read, Message.class);
                        received.handleMessage(client);
                    }
                } catch (Exception ex) {
                    client.setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    @Override
    public void sendMove(Move move) {
        if(client.isOnline()) {
            try {
                socketOut.writeUTF(Parser.toJson(move, Move.class));
                socketOut.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
                client.setActive(false);
            }
        }

    }

    @Override
    public void sendSetupper() {

    }

    @Override
    public void close() throws IOException {
        if(client.isOnline()) {
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }
}
