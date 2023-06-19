package util.Messages;

import client.network.ClientInterface;
import server.model.Game;

public class GameMessage implements Message {
    public static final String className = "GameMessage";
    private final int id;

    public GameMessage(int id) {
        this.id = id;
    }


    @Override
    public void handleMessage(ClientInterface client) {
        Game game = client.getGame();
        game.setCurrPlayerId(id);
    }

    @Override
    public String getName() {
        return className;
    }
}
