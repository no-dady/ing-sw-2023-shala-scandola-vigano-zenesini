package org.gamein.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    private static List<Game> instance;

    private ArrayList<Player> players;
    private int gameID;
    private int numPlayers;
    private Board board;
    private Pocket pocket;
    private boolean gameStarted;

    public Game() {
        this.players = new ArrayList<Player>();
//        board = new Board(); // Board instance without tiles (creates cells)
        this.pocket = new Pocket();
        this.numPlayers = 0;
        if(instance == null) {
            instance = new ArrayList<Game>(1);
        }

        this.gameID = instance.size()-1;
        instance.add(this);

    }

    public static Game newGame() {
        return new Game();
    }

    /*
     * Returns the istance of the game
     */
    public Game getInstance(int gameId) {
        if(gameId < 0 || gameId >= instance.size());
        return instance.get(gameID);
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    /*
     * @return this instance UUID
     */
    public int getGameId() {
        return this.gameID;
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
