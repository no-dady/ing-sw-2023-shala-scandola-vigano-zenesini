package client.network;

import moves.Move;
import network.Server;
import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiClientConnection extends UnicastRemoteObject implements ClientInterface {

    private final String ip;
    private final int port;
    private Server server;
    public RmiClientConnection (String ip, int port) throws RemoteException {
        super();
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void init() {

    }

    @Override
    public void sendMove(Move move) {

    }

    @Override
    public void sendSetupper() {

    }

    @Override
    public void close() {

    }
}
