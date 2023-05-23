package client;

import client.network.RMIHandler;
import network.ClientInterface;
import server.model.*;
import observer.Observer;
import observer.Observable;
import server.controller.*;
import network.Message;
import network.LastMessage;
import client.model.*;
import util.Parser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.network.Connection;
import client.network.SocketConnection;


public class Client implements ClientInterface {

    private final String ip;
    private final int port;
    private final int ui;
    private boolean online = false;
    private boolean active = true;
    private Connection handler;
    // Game Info
    private GameClient game;
    private String lobby;
    private GUI gui;
    private TUI tui;

    public DataOutputStream sokcetOut;
    public DataInputStream socketIn;
    private Thread readingThread;
    private Socket socket;

    public Client(String ip, int port, int ui, int connectionType) {
        this.ip = ip;
        this.port = port;
        this.ui = ui;
        switch(connectionType) {
            case 0: handler = new SocketConnection(ip, port);
            case 1:
                try {
                    handler = new RMIHandler(this, ip, String.valueOf(port));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            default: // Exception
        }
    }

    public synchronized boolean isActive() {
        return active;
    }

    public synchronized void setActive(boolean active) {
        this.active = active;
        if(!active) notifyAll();
    }

    public Thread asyncReadFromSocket(final DataInputStream socketIn) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    String read;
                    while (isActive()) {
                        read = socketIn.readUTF();
                        Message received = Parser.parseFromJson(read, Message.class);
                        received.handleMessage(Client.this);
                    }
                } catch (Exception e) {
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

     public void run() throws IOException {
        try{
            if(ui==1) {
                tui = new TUI(this);
                Thread t1 = new Thread(tui);
                t1.start();
            }
            else{
                GUI.entry(this);
            }
            //chose has been made
            synchronized (this){
                while (isActive()){
                    this.wait();
                }
            }
            //t1.join();
        } catch(InterruptedException | NoSuchElementException e){
            System.out.println("Connection closed from the client side");
        } finally {
            // Close Connections

            //stdin.close();
            System.exit(0);
        }
    }

    @Override
    public void closeConnection() {

    }

    @Override
    public void addObserver(Observer<String> observer) {

    }

    @Override
    public void send(String json) {

    }
}
