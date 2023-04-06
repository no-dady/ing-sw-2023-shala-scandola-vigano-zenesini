package org.gamein.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Game implements Serializable {

    public static final Random rand = new Random();
    private static final long serialVersionUID = 1L;

    private static Map<String,Game> instance;

    private ArrayList<Player> players;
    private String uuid;
    private int numPlayers;
    private Board board;
    private Pocket pocket;
    private boolean gameStarted;

    public Game() {
        this.players = new ArrayList<>();
//        board = new Board(); // Board instance without tiles (creates cells)
        this.pocket = new Pocket();
        this.numPlayers = 0;

        if(instance == null) {
            instance = new HashMap<String,Game>();
        }

        this.uuid = UUID.randomUUID().toString();
        while(instance.containsKey(uuid)) {
            this.uuid = UUID.randomUUID().toString();
        }

        instance.put(this.uuid, this);

    }

    /*
     * Returns the Singleton instance of the game if it has not been created it instanciates it as
     * well
     */
    public Game getInstance(String instanceUUID) {
        return instance.getOrDefault(instanceUUID, null);
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public String getGameUUID() {
        return this.uuid;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Board getBoard() { return this.board; }

    public Pocket getPocket() { return this.pocket; }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPocket(Pocket pocket) {
        this.pocket = pocket;
    }

    public void addPlayer(Player player) {
        if(players.size() < numPlayers)
        this.players.add(player);
    }
}
