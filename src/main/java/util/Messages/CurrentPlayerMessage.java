package util.Messages;

import client.network.ClientInterface;
import server.model.Game;

import java.rmi.RemoteException;

public class CurrentPlayerMessage implements Message {
    public static final String className = "CurrentPlayerMessage";
    private final String currentPlayerNickname;

    public CurrentPlayerMessage(String currentPlayerNickname) {
        this.currentPlayerNickname = currentPlayerNickname;
    }

    public String getCurrentPlayerNickname() {
        return currentPlayerNickname;
    }

    @Override
    public void handleMessage(ClientInterface client) throws RemoteException {
    }

    @Override
    public String getName() {
        return className;
    }
}
