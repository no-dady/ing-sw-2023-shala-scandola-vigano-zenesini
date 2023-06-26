package server.model;

import observer.Observer;
import org.javatuples.Pair;
import server.controller.BoardConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import observer.Observable;
import util.Messages.Message;

/**
 * The type Game.
 */
public class Game implements Serializable, Observable<Message>, Observer {

    private static final long serialVersionUID = 1L;

    private static List<Game> instance;
    private List<Player> players;
    private int currPlayerId;
    private int gameID;
    private int numPlayers;
    private Board board;

    private Pocket pocket;
    private boolean gameStarted;
    private List<Tile> selectedTiles;
    private Pair<CommonGoalCardStrategy , CommonGoalCardStrategy> cgcs;
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
        if(instance == null) {
            instance = new ArrayList<Game>(1);
        }

        this.gameID = instance.size()-1;
        instance.add(this);

    }
    public Game(List<Player> players, Pair<CommonGoalCardStrategy, CommonGoalCardStrategy> cgcs, Board board, Pocket pocket, int numPlayers) {
        this.players = players;
        this.cgcs = cgcs;
        this.board = board;
        this.pocket = pocket;
        this.numPlayers = numPlayers;
    }

    /**
     * New game game.
     *
     * @return the game
     */
    public static Game newGame() {
        return new Game();
    }

    public int getPlayerIndex(Player player) {
        return players.indexOf(player);
    }

    /**
     * Gets instance.
     *
     * @param gameId the game id
     * @return the instance
     */
    public Game getInstance(int gameId) {
        if(gameId < 0 || gameId >= instance.size());
        return instance.get(gameID);
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
     * Gets game id.
     *
     * @return the game id
     */
    public int getGameId() {
        return this.gameID;
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

    /**
     * Can start boolean.
     *
     * @return the boolean
     */
    public boolean canStart() {
        return players.size() == numPlayers;
    }

    /**
     * Has winner boolean.
     *
     * @return the boolean
     */
    public boolean hasWinner() {
        return players.stream().anyMatch((x) -> x.isWinner());
    }

    public void lastMessage() {
    }

    public void errorMessage(String nickName) {
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

    public boolean isGameStarted() {
        return gameStarted;
    }

    public Pair<CommonGoalCardStrategy, CommonGoalCardStrategy> getCgcs() {
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

    @Override
    public void update(Object message) {

    }
}
