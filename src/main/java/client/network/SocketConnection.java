package client.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import client.Client;
import network.Message;
import util.Parser;
import server.controller.actions.Action;

public class SocketConnection implements Connection {
    private boolean online = false;
    private boolean active = true;
    public DataOutputStream socketOut;
    private DataInputStream socketIn;
    private Socket socket;
    private Thread readingThread;

    @Override
    public void sendMove(Action move) {
        if(online) {
            try {
                socketOut.writeUTF(Parser.parseFromJson(move, Action.class));
                socketOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
                this.setActive(false);
            }
        }
    }

    public synchronized boolean isActive(){
        return active;
    }

    public synchronized void setActive(boolean active){
        this.active = active;
        if(!active) notifyAll();
    }

    public Thread asyncReadFromSocket(Client client, final DataInputStream socketIn){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String read;
                    while (isActive()) {
                        read=socketIn.readUTF();
                        //System.out.println(read);
                        Message received = Parser.parseFromJson(read, Message.class);
                        received.handleMessage(client);
                    }
                } catch (Exception e){
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    @Override
    public boolean isOnline() {
        return online;
    }
    @Override
    public void sendSetupper() {
        return;
    }

    @Override
    public void closeConnection() throws IOException {
        socketIn.close();
        socketOut.close();
        socket.close();
    }

    @Override
    public boolean setOnline(Client client) {
        try {
            this.socket = new Socket(ip, port);
            this.socketIn = new DataInputStream(socket.getInputStream());
            this.socketOut = new DataOutputStream(socket.getOutputStream());
            readingThread = asyncReadFromSocket(client, socketIn);
            online = true;
            System.out.println("Connection Established");
        } catch (IOException e) {
            setActive(false);
            return false;
        }

        return true;
    }
}
