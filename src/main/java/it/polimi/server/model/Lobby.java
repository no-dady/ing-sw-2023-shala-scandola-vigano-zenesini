 package it.polimi.server.model;

import it.polimi.client.network.ClientInterface;
import it.polimi.client.network.State;
import it.polimi.server.controller.GameController;
import it.polimi.server.exceptions.IllegalPlayersNumberException;
import it.polimi.server.network.Server;
import it.polimi.util.Messages.*;
import it.polimi.util.Parser;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Lobby class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class Lobby implements Runnable {
    private final int playerNumber;
    private LobbyStatus lobbyStatus;
    private Game game;
    private final int lobbyId;
    private final Map<String, ClientInterface> playerMap;
    private GameController controller;
    private final ArrayList<String> disconnectedPlayers = new ArrayList<>();
    private boolean active = false;
    private final String lobbyName;
    private final Server server;

    /**
     * <p>Getter for the field <code>controller</code>.</p>
     *
     * @return a {@link it.polimi.server.controller.GameController} object
     */
    public GameController getController() {
        return controller;
    }

    /**
     * <p>Constructor for Lobby.</p>
     *
     * @param playerNumber a int
     * @param adminPlayer a {@link it.polimi.client.network.ClientInterface} object
     * @param adminNickname a {@link java.lang.String} object
     * @param lobbyId a int
     * @param server a {@link it.polimi.server.network.Server} object
     */
    public Lobby(int playerNumber, ClientInterface adminPlayer, String adminNickname, int lobbyId, Server server)
    {
        this.playerNumber = playerNumber;
        this.lobbyId = lobbyId;
        this.playerMap = new HashMap<String, ClientInterface>();
        this.playerMap.put(adminNickname, adminPlayer);
        this.lobbyName = adminNickname + "'s Lobby";
        this.server = server;
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

    /**
     * <p>Getter for the field <code>lobbyStatus</code>.</p>
     *
     * @return a {@link it.polimi.server.model.LobbyStatus} object
     */
    public LobbyStatus getLobbyStatus() {
        return lobbyStatus;
    }

    /**
     * <p>isFull.</p>
     *
     * @return a boolean
     */
    public boolean isFull()
    {
        return playerMap.size() == playerNumber;
    }

    /**
     * <p>isActive.</p>
     *
     * @return a boolean
     */
    public boolean isActive(){
        return active;
    }

    /**
     * <p>Setter for the field <code>active</code>.</p>
     *
     * @param b a boolean
     */
    public void setActive(boolean b){
        active=b;
    }

    /**
     * <p>Setter for the field <code>game</code>.</p>
     *
     * @param game a {@link it.polimi.server.model.Game} object
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * <p>checkNicknameAvailable.</p>
     *
     * @param nickName a {@link java.lang.String} object
     * @return a boolean
     */
    public boolean checkNicknameAvailable(String nickName)
    {
        return !playerMap.containsKey(nickName);
    }

    /**
     * <p>addPlayer.</p>
     *
     * @param client a {@link it.polimi.client.network.ClientInterface} object
     * @param nickName a {@link java.lang.String} object
     */
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
                //System.out.println("Let's start the game");
                for (var entity : playerMap.entrySet())
                {
                    StateMessage stateMessage = new StateMessage(State.WAITINGFORGAMESTART);
                    entity.getValue().send(Parser.toJson(stateMessage, Message.class));
                }
            } else {
                StateMessage stateMessage = new StateMessage(State.WAITINGINLOBBY);
                client.send(Parser.toJson(stateMessage, Message.class));
            }
        } catch (IOException e)
        {
            System.out.println("Lobby cannot send confirm to client");
        }
    }

    /**
     * <p>Getter for the field <code>lobbyName</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getLobbyName() {
        return lobbyName;
    }

    /**
     * <p>disconnectPlayer.</p>
     *
     * @param nickname a {@link java.lang.String} object
     * @return a boolean
     * @throws java.rmi.RemoteException if any.
     */
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
        } else throw new IllegalArgumentException();

        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void run() {
        try
        {
            lobbyStatus = LobbyStatus.Playing;
            //System.out.println("Creating Game");
            controller = new GameController(this);
            controller.createLobby(playerNumber, playerMap.keySet().stream().toList());
            game = controller.getGame();
            if (game == null) { return; }
            for (var entry : playerMap.entrySet()) {
                InitialMessage initialMessage = new InitialMessage(game);
                entry.getValue().send(Parser.toJson(initialMessage, Message.class));
                BoardMessage boardMessage = new BoardMessage(game.getBoard());
                entry.getValue().send(Parser.toJson(boardMessage, Message.class));
            }
            setActive(true);
            controller.start();
        } catch (IllegalPlayersNumberException | IOException e)
        {
            System.out.println(e.getMessage());
        } finally
        {
            server.removeLobby(lobbyId);
        }
    }

    /**
     * <p>getConnections.</p>
     *
     * @return a {@link java.util.Map} object
     */
    public Map<String, ClientInterface> getConnections() {
        return playerMap;
    }

    /**
     * <p>clear.</p>
     */
    public void clear() {
        if(game!=null){
            //game.close();
            game=null;
            controller=null;
        }
        playerMap.clear();
        disconnectedPlayers.clear();
        active=false;
    }
}
