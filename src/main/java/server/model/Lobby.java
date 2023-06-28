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
import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lobby implements Runnable {
    private final int playerNumber;

    private LobbyStatus lobbyStatus;

    private Game game;

    private int lobbyId;

    private Map<String, ClientInterface> playerMap;

    private HashMap<String, View> playersview= new HashMap<>();

    private GameController controller;

    private final ArrayList<String> disconnectedPlayers = new ArrayList<>();
    private boolean active = false;

    private String lobbyName;

    public GameController getController() {
        return controller;
    }

    public Lobby(int playerNumber, ClientInterface adminPlayer, String adminNickname, int lobbyId)
    {
        this.playerNumber = playerNumber;
        this.lobbyId = lobbyId;
        this.playerMap = new HashMap<String, ClientInterface>();
        this.playerMap.put(adminNickname, adminPlayer);
        this.lobbyName = adminNickname + "'s Lobby";
        lobbyStatus = LobbyStatus.Setup;
        try
        {
            StateMessage stateMessage = new StateMessage(State.WAITINGINLOBBY);
            adminPlayer.send(Parser.toJson(stateMessage, Message.class));
            JoinedMessage messageItself = new JoinedMessage(adminNickname, lobbyId);
            adminPlayer.send(Parser.toJson(messageItself, Message.class));
        } catch(RemoteException e)
        {
            System.out.println("Cannot send the nickMessage to admin");
        }
    }

    public int getPlayerNumber() {
        return playerNumber;
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
        return !playerMap.containsKey(nickName);
    }

    public void addPlayer(ClientInterface client, String nickName)
    {
        try
        {
            for (var entry : playerMap.entrySet())
            {
                JoinedMessage messageToSendForOther = new JoinedMessage(nickName, lobbyId);
                entry.getValue().send(Parser.toJson(messageToSendForOther, Message.class));
                JoinedMessage messageToSendJoined = new JoinedMessage(entry.getKey(), lobbyId);
                client.send(Parser.toJson(messageToSendJoined, Message.class));
            }
            this.playerMap.put(nickName, client);
            JoinedMessage messageItself = new JoinedMessage(nickName, lobbyId);
            client.send(Parser.toJson(messageItself, Message.class));
            if (this.playerMap.size() == playerNumber)
            {
                //messageToSend = new ConfirmMessage("Joined " + lobbyName, 3);
                //client.send(Parser.toJson(messageToSend, ConfirmMessage.class));
                //for (var entry : playerMap.entrySet())
                //{
                //    JoinedMessage joinedMessage = new JoinedMessage(entry.getKey());
                //    client.send(Parser.toJson(joinedMessage, JoinedMessage.class));
                //}
                System.out.println("Let's start the game");
                for (var entity : playerMap.entrySet())
                {
                    StateMessage stateMessage = new StateMessage(State.WAITINGFORGAMESTART);
                    entity.getValue().send(Parser.toJson(stateMessage, Message.class));
                }
            } else {
                StateMessage stateMessage = new StateMessage(State.WAITINGINLOBBY);
                client.send(Parser.toJson(stateMessage, Message.class));
            }
        } catch (IOException e)//| IllegalPlayersNumberException e)
        {
            System.out.println("Lobby cannot send confirm to client");
        } //catch (InterruptedException e) {
        //catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //} catch (IllegalPlayersNumberException e) {
        //    throw new RuntimeException(e);
        //}
        // throw new RuntimeException(e);
        //}
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

    //public void reconnectPlayer(String nickname, ClientInterface conn) throws RemoteException {
    //    if(disconnectedPlayers.contains(nickname)) {
    //        playerMap.put(nickname, conn);
    //        playersview.get(nickname).setOffline(false);
    //        ((RemoteView) playersview.get(nickname)).setClientConnection(conn);
    //        playersview.get(nickname).sendInitialMessage(game, getLobbyName());
    //        for(String name: playerMap.keySet()) {
    //            playerMap.get(name).send(Parser.toJson(new ReconnectMessage(playerMap.keySet(), nickname), Message.class));
    //        }
//
    //        disconnectedPlayers.remove(nickname);
    //    } else throw new IllegalArgumentException();
    //}

    @Override
    public void run() {
        //for (var entry : playerMap.entrySet()) {

            //ConfirmMessage messageToSend = new ConfirmMessage("Hi " + entry.getKey() + ", all " + playerNumber + " players have joined, now the game will start" + entry.getValue().getState(), 4);
            //try
            //{
            //    entry.getValue().send(Parser.toJson(messageToSend, ConfirmMessage.class));
            //} catch(RemoteException e)
            //{
            //    System.out.println("Cannot send ConfirmMessage from server lobby to client");
            //}
        //}]
        try
        {
            lobbyStatus = LobbyStatus.Playing;
            System.out.println("Creating Game");
            controller = new GameController(this);
            controller.createLobby(playerNumber, playerMap.keySet().stream().toList());
            game = controller.getGame();
            if (game!= null){
                System.out.println(game);
            }
            else {
                return;
            }
            for (var entry : playerMap.entrySet()) {
                InitialMessage initialMessage = new InitialMessage(game);
                entry.getValue().send(Parser.toJson(initialMessage, Message.class));
                //ConfirmMessage messageToSend = new ConfirmMessage("Hi " + entry.getKey() + ", now you have the game model", 5);
                //try
                //{
                //   entry.getValue().send(Parser.toJson(messageToSend, ConfirmMessage.class));
                //} catch(RemoteException e)
                //{
                //    System.out.println("Cannot send ConfirmMessage from server lobby to client");
                //}
            }
            setActive();
            controller.start();
        } catch (IllegalPlayersNumberException | IOException | InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
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
}
