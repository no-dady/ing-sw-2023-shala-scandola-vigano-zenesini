package org.gamein.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Game implements Serializable {

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

    /*
     * @return this instance UUID
     */
    public String getGameUUID() {
        return this.uuid;
    }

    /*
     * @return all players of the game
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /*
     * @return the board of the game
     */
    public Board getBoard() { return this.board; }

    /*
     * @return the pocket instance
     */
    public Pocket getPocket() { return this.pocket; }

    /*
     * @param player new player joined
     */
    public void addPlayer(Player player) {
        if(players.size() < numPlayers)
            this.players.add(player);
    }

    /*
     * @return True if all players have joined the game
     */
    public boolean canStart() {
        return players.size() == numPlayers;
    }

    /*
     * @return True if game has winner
     */
    public boolean hasWinner() {
        return players.stream().anyMatch((x) -> x.isWinner());
    }

    /*
    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPocket(Pocket pocket) {
        this.pocket = pocket;
    }
    */

}
