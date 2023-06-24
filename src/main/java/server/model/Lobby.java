 package server.model;

import client.network.ClientInterface;
import client.network.State;
import server.controller.GameController;
import server.exceptions.IllegalPlayersNumberException;
import server.view.RemoteView;
import server.view.View;
import util.Messages.*;
import util.Parser;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lobby {
    private final int playerNumber;

    private LobbyStatus lobbyStatus;

    private Game game;

    private Map<String, ClientInterface> playerMap;

    private HashMap<String, View> playersview= new HashMap<>();

    private GameController controller;

    private final ArrayList<String> disconnectedPlayers = new ArrayList<>();
    private boolean active = false;

    private String lobbyName;

    public Lobby(int playerNumber, ClientInterface adminPlayer, String adminNickname)
    {
        this.playerNumber = playerNumber;
        this.playerMap = new HashMap<String, ClientInterface>();
        this.playerMap.put(adminNickname, adminPlayer);
        this.lobbyName = adminNickname + "'s Lobby";
        lobbyStatus = LobbyStatus.Setup;
        try
        {
            JoinedMessage joinedMessage = new JoinedMessage(adminNickname);
            adminPlayer.send(Parser.toJson(joinedMessage, JoinedMessage.class));
        } catch(RemoteException e)
        {
            System.out.println("Cannot send the nickMessage to admin");
        }
    }

    public LobbyStatus getLobbyStatus() {
        return lobbyStatus;
    }

    public boolean isFull()
    {
        return playerMap.size() == playerNumber;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(){
        active=true;
    }

    public void setGame(Game game) {
        this.game = game;
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
                ConfirmMessage messageToSendForOther = new ConfirmMessage(nickName + " joined the lobby", 2);
                entry.getValue().send(Parser.toJson(messageToSendForOther, ConfirmMessage.class));
            }
            this.playerMap.put(nickName, clientInterface);
            ConfirmMessage messageToSend;
            if (this.playerMap.size() == playerNumber)
            {
                messageToSend = new ConfirmMessage("Joined " + lobbyName, 3);
                clientInterface.send(Parser.toJson(messageToSend, ConfirmMessage.class));
                for (var entry : playerMap.entrySet())
                {
                    JoinedMessage joinedMessage = new JoinedMessage(entry.getKey());
                    clientInterface.send(Parser.toJson(joinedMessage, JoinedMessage.class));
                }
                startGame();
            } else {
                messageToSend = new ConfirmMessage("Joined " + lobbyName, 1);
                clientInterface.send(Parser.toJson(messageToSend, ConfirmMessage.class));
                for (var entry : playerMap.entrySet())
                {
                    JoinedMessage joinedMessage = new JoinedMessage(entry.getKey());
                    clientInterface.send(Parser.toJson(joinedMessage, JoinedMessage.class));
                }
            }
        } catch (IOException | IllegalPlayersNumberException e)
        {
            System.out.println("Lobby cannot send confirm to client");
        }
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public boolean disconnectPlayer(String nickname) throws RemoteException {
        if(playerMap.remove(nickname) != null) {
            if(isActive()) {
                disconnectedPlayers.add(nickname);
            }

            if(playerMap.size() == 0) {
                clear();
                return true;
            }

            for(String name: playerMap.keySet()) {
                playerMap.get(name).send(Parser.toJson(new DisconnectMessage(playerMap.keySet(), nickname), Message.class));
            }

            try {
                playersview.get(nickname).setOffline(true);
            } catch (NullPointerException dc) {
            }
        } else throw new IllegalArgumentException();

        return false;
    }

    public void reconnectPlayer(String nickname, ClientInterface conn) throws RemoteException {
        if(disconnectedPlayers.contains(nickname)) {
            playerMap.put(nickname, conn);
            playersview.get(nickname).setOffline(false);
            ((RemoteView) playersview.get(nickname)).setClientConnection(conn);
            playersview.get(nickname).sendInitialMessage(game, getLobbyName());
            for(String name: playerMap.keySet()) {
                playerMap.get(name).send(Parser.toJson(new ReconnectMessage(playerMap.keySet(), nickname), Message.class));
            }

            disconnectedPlayers.remove(nickname);
        } else throw new IllegalArgumentException();
    }

    public void startGame() throws IllegalPlayersNumberException, RemoteException {
        for (var entry : playerMap.entrySet()) {

            ConfirmMessage messageToSend = new ConfirmMessage("Hi " + entry.getKey() + ", all " + playerNumber + " players have joined, now the game will start" + entry.getValue().getState(), 4);
            try
            {
                entry.getValue().send(Parser.toJson(messageToSend, ConfirmMessage.class));
            } catch(RemoteException e)
            {
                System.out.println("Cannot send ConfirmMessage from server lobby to client");
            }
        }
        lobbyStatus = LobbyStatus.Playing;
        System.out.println("Creating Game");
        controller = new GameController();
        System.out.println("eccoci qui");
        controller.createLobby(playerNumber, playerMap.keySet().stream().toList());
        game = controller.getGame();
        if (game!= null){
            System.out.println(game);
        }
        else {
            System.out.println("qualcosa non va, game è null");
            return;
        }
        System.out.println("eccoci qui invece ora");
        for (var entry : playerMap.entrySet()) {
            entry.getValue().setGame(game);
            StateMessage stateMessage = new StateMessage(State.MYTURN);
            entry.getValue().send(Parser.toJson(stateMessage, StateMessage.class));
            System.out.println(entry.getValue().getState());
            System.out.println(entry.getValue().getGame());
            ConfirmMessage messageToSend = new ConfirmMessage("Hi " + entry.getKey() + ", now you have the game model" + entry.getValue().getState(), 5);
            try
            {
                entry.getValue().send(Parser.toJson(messageToSend, ConfirmMessage.class));
            } catch(RemoteException e)
            {
                System.out.println("Cannot send ConfirmMessage from server lobby to client");
            }
        }

        setActive();
    }

    public boolean findDisconnectedPlayers(String nickname) {
        return disconnectedPlayers.contains(nickname);
    }

    public Map<String, ClientInterface> getConnections() {
        return playerMap;
    }

    public void clear() {
        if(game!=null){
            //game.close();
            game=null;
            controller=null;
        }
        playerMap.clear();
        playersview.clear();
        disconnectedPlayers.clear();
        active=false;
    }

    public void closeAllConnections()
    {
        try
        {
            for (var entity : playerMap.entrySet())
            {
                entity.getValue().closeConnection();
            }
        } catch(RemoteException e)
        {
            System.out.println("Cannot close connection of the clients");
        }
        clear();
    }
}
