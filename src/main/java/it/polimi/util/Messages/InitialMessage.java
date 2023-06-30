package it.polimi.util.Messages;

import it.polimi.client.Client;
import it.polimi.server.model.Game;

import java.rmi.RemoteException;

public class InitialMessage implements Message {
    public static final String className = "InitialMessage";
    private final Game game;

    public InitialMessage(Game model) {
        this.game = model;
    }

    @Override
    public void handleMessage(Client client) {
        client.setGame(game);
    }

    @Override
    public String getName() {
        return className;
    }
}
