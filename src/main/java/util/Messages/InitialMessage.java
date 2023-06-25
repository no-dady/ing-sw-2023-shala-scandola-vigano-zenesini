package util.Messages;

import client.Client;
import server.model.Bookshelf;
import server.model.Game;
import server.model.PersonalGoalCard;

import java.rmi.RemoteException;

public class InitialMessage implements Message {
    public static final String className = "InitialMessage";
    private final Game model;
    private final int id;
    private final Bookshelf bookshelf;
    private final PersonalGoalCard personalGoalCard;
    // private final String lobby;

    public InitialMessage(Game model, int id, Bookshelf bookshelf, PersonalGoalCard personalGoalCard) {
        this.model = model;
        this.id = id;
        this.bookshelf = bookshelf;
        this.personalGoalCard = personalGoalCard;
    }

    @Override
    public void handleMessage(Client client) throws RemoteException {
        //client.setGame(model);
        Game game = client.getGame();
        // client.setLobby(lobby);
    }

    @Override
    public String getName() {
        return className;
    }
}
