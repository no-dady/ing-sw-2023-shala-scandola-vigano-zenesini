package util.Messages;

import client.Client;
import client.network.ClientInterface;
import server.model.Game;

import java.rmi.RemoteException;

public class GameMessage implements Message {
    public static final String className = "GameMessage";
    private final int id;

    public GameMessage(int id) {
        this.id = id;
    }


    @Override
    public void handleMessage(Client client) throws RemoteException {
        Game game = client.getGame();
        game.setCurrPlayerId(id);
    }

    @Override
    public String getName() {
        return className;
    }
}
