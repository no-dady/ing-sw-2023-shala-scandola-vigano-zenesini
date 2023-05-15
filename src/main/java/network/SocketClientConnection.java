package network;

import observer.Observer;
import observer.Observable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

public class SocketClientConnection implements Observable<String>, ClientInterface, Runnable {

    private final Socket socket;
    private DataOutputStream out;
    private final Server server;
    private String nickName;
    private Hub lobby;
    private transient final List<Observer<String>> observers = new ArrayList<>();

    private boolean active = true;
    private final boolean standby= false;

    @Override
    public synchronized void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
    }

    @Override
    public void run() {}

    @Override
    public void send(String json) {
        try {
            out.writeUTF(json);
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void addObserver(Observer<String> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    @Override
    public void notify(String message) {
        synchronized (observers) {
            for(Observer<String> observer : observers) {
                observer.update(message);
            }
        }
    }

}
