package it.polimi.client.network;

import it.polimi.client.Client;
import it.polimi.server.network.ServerInterface;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * The type Server stub.
 *
 * @author daniel
 * @version $Id: $Id
 */
//It implements the Server, but it will be used on the it.polimi.client-side to communicate
public class ClientSocketMiddleware implements ServerInterface, Runnable {
    private final Client client;
    private DataInputStream in;
    private DataOutputStream out;

    private final Socket socket;

    private final ClientInterface clientinterface;

    /**
     * <p>Constructor for ClientSocketMiddleware.</p>
     *
     * @param client a {@link it.polimi.client.Client} object
     * @param ip a {@link java.lang.String} object
     * @param port a int
     * @param clientInterface a {@link it.polimi.client.network.ClientInterface} object
     * @throws java.io.IOException if any.
     */
    public ClientSocketMiddleware(Client client, String ip, int port, ClientInterface clientInterface) throws IOException {
        this.client = client;
        this.clientinterface = clientInterface;

        socket = new Socket(ip, port);
    }

    /** {@inheritDoc} */
    @Override
    public void run()
    {
        try
        {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            String read;
            while (client.isActive()) {
                int whatIsSending;
                whatIsSending = in.readInt();
                switch(whatIsSending)
                {
                    case 0:
                        read = in.readUTF();
                        clientinterface.send(read);
                        break;

                    case 1:
                        client.setActive(false);
                        break;
                }
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

    /** {@inheritDoc} */
    @Override
    public void register(ClientInterface client) throws RemoteException
    {}

    /** {@inheritDoc} */
    @Override
    public void sendAction(String json) throws RemoteException
    {
        try
        {
            out.writeInt(0);
            out.writeUTF(json);
            out.flush();
        }
        catch (IOException e)
        {
            throw new RemoteException("Cannot send string Message from it.polimi.client", e);
        }
    }

    /** {@inheritDoc} */
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
            throw new RemoteException("Cannot send string SetupFirst from it.polimi.client", e);
        }
    }

    /** {@inheritDoc} */
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
            throw new RemoteException("Cannot send string SetupAll from it.polimi.client", e);
        }
    }
}
