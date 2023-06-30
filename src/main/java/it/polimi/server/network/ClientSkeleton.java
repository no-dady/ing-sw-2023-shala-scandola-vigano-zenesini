package it.polimi.server.network;

import it.polimi.client.network.ClientInterface;
import it.polimi.server.model.Lobby;
import it.polimi.setup.Setup;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;


/**
 * <p>ClientSkeleton class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class ClientSkeleton implements ClientInterface, Runnable {

    private final Socket socket;

    private final Server server;

    private String nickName;

    private Lobby lobby;

    private DataOutputStream out;

    private boolean isActive = false;


    /**
     * <p>Constructor for ClientSkeleton.</p>
     *
     * @param socket a {@link java.net.Socket} object
     * @param server a {@link it.polimi.server.network.Server} object
     * @throws java.rmi.RemoteException if any.
     */
    public ClientSkeleton(Socket socket, Server server) throws RemoteException {
        this.socket = socket;
        this.server = server;
    }

    private boolean recNickname(Setup setup) {
        return setup.getParameter() != null;
    }

    /** {@inheritDoc} */
    @Override
    public void send(String json) throws RemoteException {
        try {
            out.writeInt(0);
            out.writeUTF(json);
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void run() {
        //System.out.println("Start thread");
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            boolean confirm = false;
            isActive = true;
            server.register(this);

            receive(in);

            in.close();
            out.close();
            socket.close();
            System.out.println("Disconnected");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void receive(DataInputStream in)
    {
        try
        {
            while (isActive) {
                int whatIsSending = in.readInt();
                String rec = in.readUTF();
                switch (whatIsSending) {
                    case 0:
                        server.sendAction(rec);
                        break;

                    case 1:
                        server.sendSetupFirst(rec);
                        break;

                    case 2:
                        server.sendSetupAll(rec);
                        break;
                }
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void close() {
        try {
            out.writeInt(1);
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * <p>getNickname.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getNickname() {
        return this.nickName;
    }

    /**
     * <p>isActive.</p>
     *
     * @return a boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * <p>closeConnection.</p>
     */
    public void closeConnection()
    {
        isActive = false;
    }

}
