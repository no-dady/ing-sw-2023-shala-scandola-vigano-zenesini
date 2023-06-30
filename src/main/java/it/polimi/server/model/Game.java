package it.polimi.server.model;

import it.polimi.observer.Observer;
import it.polimi.server.controller.BoardConfig;
import java.io.Serializable;
import java.util.*;

import it.polimi.observer.Observable;
import it.polimi.util.Exclude;
import it.polimi.util.Messages.Message;

/**
 * The type Game.
 *
 * @author daniel
 * @version $Id: $Id
 */
public class Game implements Serializable, Observable<Message> {

    private static final long serialVersionUID = 1L;

    private final List<Player> players;
    private int currPlayerId;
    private final int numPlayers;
    @Exclude
    private Board board;

    private final Pocket pocket;
    private boolean gameStarted;
    private List<Tile> selectedTiles;
    public Set<CommonGoalCardStrategy> cgcs;
    private String currPlayerNick;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.players = new ArrayList<>();
        this.cgcs = CommonGoalCardStrategy.getRandomCards();
        this.board = new Board(BoardConfig.newEmptyBoard());
        this.pocket = new Pocket();
        this.numPlayers = 0;

    }
    /**
     * <p>Constructor for Game.</p>
     *
     * @param players a {@link java.util.List} object
     * @param cgcs a {@link java.util.Set} object
     * @param board a {@link it.polimi.server.model.Board} object
     * @param pocket a {@link it.polimi.server.model.Pocket} object
     * @param numPlayers a int
     */
    public Game(List<Player> players, Set<CommonGoalCardStrategy> cgcs, Board board, Pocket pocket, int numPlayers) {
        this.players = players;
        this.cgcs = cgcs;
        this.board = board;
        this.pocket = pocket;
        this.numPlayers = numPlayers;
    }


    /**
     * Sets game started.
     *
     * @param gameStarted the game started
     */
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) this.players;
    }
    /**
     * <p>getPlayerByNickname.</p>
     *
     * @param nickName a {@link java.lang.String} object
     * @return a {@link it.polimi.server.model.Player} object
     */
    public Player getPlayerByNickname(String nickName){
        for (Player p: players
             ) {if (p.getUserName().equals(nickName)){
                 return p;}

        }
        //System.out.println("no such player");
        return null;
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    public Board getBoard() { return this.board; }

    /**
     * <p>Setter for the field <code>board</code>.</p>
     *
     * @param board a {@link it.polimi.server.model.Board} object
     */
    public void setBoard(Board board) {
        this.board = board;
    }
    /* @return the selected tiles during a turn
     */
    /**
     * <p>Getter for the field <code>selectedTiles</code>.</p>
     *
     * @return a {@link java.util.List} object
     */
    public List<Tile> getSelectedTiles() {
        return selectedTiles;
    }
    /*
     * @param the selected tiles during a turn
     */
    /**
     * <p>Setter for the field <code>selectedTiles</code>.</p>
     *
     * @param selectedTiles a {@link java.util.List} object
     */
    public void setSelectedTiles(List<Tile> selectedTiles) {
        this.selectedTiles = selectedTiles;
    }

    /**
     * Gets pocket.
     *
     * @return the pocket
     */
    public Pocket getPocket() {
        return this.pocket;
    }

    /**
     * Add player.
     *
     * @param player the player
     */
    public void addPlayer(Player player) {
        if(players.size() < numPlayers)
            this.players.add(player);
    }

    /**
     * <p>Getter for the field <code>currPlayerId</code>.</p>
     *
     * @return a int
     */
    public int getCurrPlayerId() {
        return currPlayerId;
    }

    /**
     * <p>Setter for the field <code>currPlayerId</code>.</p>
     *
     * @param currPlayerId a int
     */
    public void setCurrPlayerId(int currPlayerId) {
        this.currPlayerId = currPlayerId;
    }

    private transient final List<Observer<Message>> observers = new ArrayList<>();

    /**
     * <p>Getter for the field <code>numPlayers</code>.</p>
     *
     * @return a int
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * <p>Getter for the field <code>cgcs</code>.</p>
     *
     * @return a {@link java.util.Set} object
     */
    public Set<CommonGoalCardStrategy> getCgcs() {
        return cgcs;
    }

    /** {@inheritDoc} */
    @Override
    public void addObserver(Observer<Message> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void notify(Message message) {
        synchronized (observers) {
            for(Observer<Message> observer : observers){
                observer.update(message);
            }
        }
    }

    /**
     * <p>Getter for the field <code>currPlayerNick</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getCurrPlayerNick() {
        return this.currPlayerNick;
    }
    /**
     * <p>Setter for the field <code>currPlayerNick</code>.</p>
     *
     * @param currPlayerNick a {@link java.lang.String} object
     */
    public void setCurrPlayerNick(String currPlayerNick) {
         this.currPlayerNick = currPlayerNick;
    }

}
