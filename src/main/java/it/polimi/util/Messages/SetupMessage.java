package it.polimi.util.Messages;

import it.polimi.client.Client;

import java.rmi.RemoteException;

public class SetupMessage implements Message {
    public static final String className = "SetupMessage";

    private final String nickname;
    private final Integer numOfPlayers;

    public SetupMessage(String nickname, Integer numOfPlayers) {
        this.nickname = nickname;
        this.numOfPlayers = numOfPlayers;
    }

    @Override
    public void handleMessage(Client client) throws RemoteException {

    }

    @Override
    public String getName() {
        return className;
    }
}
