package it.polimi.util.Messages;

import it.polimi.client.Client;
import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.Game;
import it.polimi.server.model.Player;

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
    public void handleMessage(Client client) throws RemoteException {
        Game game = client.getGame();
        Player owner = game.getPlayers().get(idPlayer);
        owner.setPersonalBookshelf(bookshelf);
    }

    @Override
    public String getName() {
        return className;
    }
}
