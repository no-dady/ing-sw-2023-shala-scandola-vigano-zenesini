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
    private DataOutputStream dout;
    private final ServerImpl server;
    private String nickName;
    private Hub lobby;
    private transient final List<Observer<String>> observers = new ArrayList<>();

    private boolean active = true;
    private final boolean standby= false;

    public SocketClientConnection(Socket socket, ServerImpl server)
    {
        this.socket = socket;
        this.server = server;
    }

    public void setActive(boolean active) { this.active = active; }

    public synchronized boolean isActive() { return active; }

    private void endCommunication(String nickName)
    {
        System.out.println("Action ended by " + nickName);
    }

    @Override
    public synchronized void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
    }

    @Override
    public void run() {
        DataInputStream din;

        try
        {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            String read;
            boolean confirm = false;
            int numberOfPlayer = 0;
            while (!isActive())
            {
                read = din.readUTF();
                System.out.println("Received: " + read);
                notify(read);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            setActive(false);
            endCommunication(nickname);
        }
    }

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
