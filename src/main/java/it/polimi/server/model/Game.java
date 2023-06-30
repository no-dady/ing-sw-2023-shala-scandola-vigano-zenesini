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
 */
public class Game implements Serializable, Observable<Message> {

    private static final long serialVersionUID = 1L;

    private List<Player> players;
    private int currPlayerId;
    private int numPlayers;
    @Exclude
    private Board board;

    private Pocket pocket;
    private boolean gameStarted;
    private List<Tile> selectedTiles;
    public Set<CommonGoalCardStrategy> cgcs;
    private String currPlayerNick;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.players = new ArrayList<>();
        this.cgcs = CommonGoalCardStrategy.getRandomCards();;
        this.board = new Board(BoardConfig.newEmptyBoard());
        this.pocket = new Pocket();
        this.numPlayers = 0;

    }
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
    public Player getPlayerByNickname(String nickName){
        for (Player p: players
             ) {if (p.getUserName().equals(nickName)){
                 return p;}

        };
        System.out.println("no such player");
        return null;
    }

    /**
     * Gets board.
     *
     * @return the board
    **/
    public Board getBoard() { return this.board; }

    public void setBoard(Board board) {
        this.board = board;
    }
    /* @return the selected tiles during a turn
     */
    public List<Tile> getSelectedTiles() {
        return selectedTiles;
    }
    /*
     * @param the selected tiles during a turn
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

    public int getCurrPlayerId() {
        return currPlayerId;
    }

    public void setCurrPlayerId(int currPlayerId) {
        this.currPlayerId = currPlayerId;
    }

    private transient final List<Observer<Message>> observers = new ArrayList<>();

    public int getNumPlayers() {
        return numPlayers;
    }

    public Set<CommonGoalCardStrategy> getCgcs() {
        return cgcs;
    }

    @Override
    public void addObserver(Observer<Message> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    @Override
    public void notify(Message message) {
        synchronized (observers) {
            for(Observer<Message> observer : observers){
                observer.update(message);
            }
        }
    }

    public String getCurrPlayerNick() {
        return this.currPlayerNick;
    }
    public void setCurrPlayerNick(String currPlayerNick) {
         this.currPlayerNick = currPlayerNick;
    }

}
