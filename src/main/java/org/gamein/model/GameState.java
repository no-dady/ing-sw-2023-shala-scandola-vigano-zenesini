package org.gamein.model;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Player> players;
    private int turnNumber;
    private Board board;
    private Pocket pocket;

    public GameState(ArrayList<Player> players, Board board, Pocket pocket)
    {
        this.players = players;
        this.board = board;
        this.pocket = pocket;
        this.turnNumber = 0;
    }

    public Player getSinglePlayer(int index) {
        return this.players.get(index);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Board getBoard() { return this.board; }

    public Pocket getPocket() { return this.pocket; }

}
