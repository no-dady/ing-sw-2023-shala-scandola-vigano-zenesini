package server.model;

import client.network.ClientInterface;
import server.controller.GameController;
import server.exceptions.IllegalPlayersNumberException;
import util.Messages.ConfirmMessage;
import util.Parser;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class Lobby {
    private final int playerNumber;

    private LobbyStatus lobbyStatus;

    private Map<String, ClientInterface> playerMap;

    private GameController controller;

    private String lobbyName;

    public Lobby(int playerNumber, ClientInterface adminPlayer, String adminNickname)
    {
        this.playerNumber = playerNumber;
        this.playerMap = new HashMap<String, ClientInterface>();
        this.playerMap.put(adminNickname, adminPlayer);
        this.lobbyName = adminNickname + "'s Lobby";
        lobbyStatus = LobbyStatus.Setup;
    }

    public LobbyStatus getLobbyStatus() {
        return lobbyStatus;
    }

    public boolean isFull()
    {
        return playerMap.size() == playerNumber;
    }

    public boolean checkNicknameAvailable(String nickName)
    {
        return playerMap.containsKey(nickName);
    }

    public void addPlayer(ClientInterface clientInterface, String nickName)
    {
        try
        {
            for (var entry : playerMap.entrySet())
            {
                ConfirmMessage messageToSendForOther = new ConfirmMessage(nickName + " joined the lobby");
                entry.getValue().send(Parser.toJson(messageToSendForOther, ConfirmMessage.class));
            }
            this.playerMap.put(nickName, clientInterface);
            ConfirmMessage messageToSend = new ConfirmMessage("Joined " + lobbyName);
            clientInterface.send(Parser.toJson(messageToSend, ConfirmMessage.class));
        } catch (IOException e)
        {
            System.out.println("Lobby cannot send confirm to client");
        }
        if (this.playerMap.size() == playerNumber)
        {
            try {
                startGame();
            } catch (IllegalPlayersNumberException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void startGame() throws IllegalPlayersNumberException {
        for (var entry : playerMap.entrySet()) {
            ConfirmMessage messageToSend = new ConfirmMessage("Hi " + entry.getKey() + ", all " + playerNumber + " players have joined, now the game will start");
            try
            {
                entry.getValue().send(Parser.toJson(messageToSend, ConfirmMessage.class));
            } catch(RemoteException e)
            {
                System.out.println("Cannot send ConfirmMessage from server lobby to client");
            }
        }
        lobbyStatus = LobbyStatus.Playing;
        controller = new GameController();
        controller.createLobby(playerNumber, playerMap.keySet().stream().toList());
    }
}
