package util.Messages;

import client.Client;
import client.network.ClientInterface;
import server.model.Bookshelf;
import server.model.Game;
import server.model.Player;

import java.rmi.RemoteException;

public class BookshelfMessage implements Message {
    public static final String className = "BookshelfMessage";
    private final Bookshelf bookshelf;
    private final int id;

    public BookshelfMessage(int id, Bookshelf bookshelf) {
        this.id = id;
        this.bookshelf = bookshelf;
    }
    @Override
    public void handleMessage(Client client) throws RemoteException {
        Game game = client.getGame();
        Player owner = game.getPlayers().get(id);

        owner.setPersonalBookshelf(bookshelf);
    }

    @Override
    public String getName() {
        return className;
    }
}
