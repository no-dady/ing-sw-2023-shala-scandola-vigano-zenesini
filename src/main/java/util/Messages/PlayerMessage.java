package util.Messages;

import client.network.ClientInterface;
import server.model.Bookshelf;
import server.model.Game;
import server.model.Player;

import java.rmi.RemoteException;

public class PlayerMessage implements Message {
    public static final String className = "PlayerMessage";
    private final int idPlayer;
    private final Bookshelf bookshelf;
    private final ErrorMessage error;

    public PlayerMessage(int id, Bookshelf bookshelf, ErrorMessage error) {
        this.idPlayer = id;
        this.bookshelf = bookshelf;
        this.error = error;
    }
    @Override
    public void handleMessage(ClientInterface client) throws RemoteException {
        Game game = client.getGame();
        Player owner = game.getPlayers().get(idPlayer);
        owner.setPersonalBookshelf(bookshelf);
    }

    @Override
    public String getName() {
        return className;
    }
}
