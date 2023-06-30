package it.polimi.server.model;
import com.google.gson.reflect.TypeToken;
import it.polimi.server.cgc.*;
import it.polimi.setup.ConfigsFromJson;
import it.polimi.util.Messages.Message;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import it.polimi.observer.Observable;
import it.polimi.observer.Observer;
import it.polimi.util.Parser;

/**
 * The type Common goal card strategy.
 *
 * @author daniel
 * @version $Id: $Id
 */
@SuppressWarnings("StaticInitializerReferencesSubClass")
public abstract class CommonGoalCardStrategy implements Serializable, Observable<Message> {
    /**
     * The List common goal list.
     */
    protected static List<CommonGoalCardStrategy> listCommonGoalList;
    private Queue<Integer> points = new LinkedList<>();
    private final String nameCgC = "";

    private int numPlayers;

    /**
     * <p>getClassName.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public abstract String getClassName();

    /**
     * Condition check boolean.
     *
     * @param shelf the shelf
     * @return the boolean
     */
    public abstract boolean conditionCheck (Tile[][] shelf);

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getName() {
        return nameCgC;
    }
    private final HashMap<String, Integer> completedMap = new HashMap<>();
    /**
     * <p>Print.</p>
     *
     * @throws java.io.IOException if any.
     */
    public void Print() throws IOException {
        System.out.println(ConfigsFromJson.getArt(Parser.getResourcePath("json/cgcArts/" + this.getName() + ".json")));
    }

    static {
        listCommonGoalList = new ArrayList<CommonGoalCardStrategy>();
        // 2 squares of 4 tiles of the same type
        listCommonGoalList.add(new SquareCheck(2, "2Squares"));
        // 2 columns made of all distinct tiles
        listCommonGoalList.add(new StraightDirection(2, Bookshelf.getCols(), false, true, "2FullDistinctCols"));
        // 4 vertical strips of 4 tiles of the same type
        listCommonGoalList.add(new StraightDirection(4,4,true,true, "4EqualColsOf4"));
        // 6 vertical strips of 2 tiles of the same type
        listCommonGoalList.add(new StraightDirection(6,2,true,true, "6EqualColsOf2"));
        // 4 rows full of tiles with at least 2 tiles of the same type
        listCommonGoalList.add(new MaxNTypes(false, 3, 4, "4RowsMax3Diff"));
        // listCommonGoalList.add(new StraightDirection(4,2,true,false, "4RowsMax3Diff"));
        // 3 columns full of tiles with at least 3 tiles of the same type
        listCommonGoalList.add(new MaxNTypes(true, 3, 3, "3ColsMax3Diff"));
        //listCommonGoalList.add(new StraightDirection(3,3,true,true, "3ColsMax3Diff"));
        // 2 rows full of distinct tiles
        listCommonGoalList.add(new StraightDirection(2,Bookshelf.getRows(),false,false, "2FullDistinctRows"));
        // = . . . =
        // . . . . .
        // . . . . .
        // . . . . .
        // . . . . .
        // = . . . =
        listCommonGoalList.add(new SquareCheck(1, "4EqualCorners"));
        // . = . = .
        // . . . . .
        // = . = . =
        // . . . . .
        // = . = . =
        listCommonGoalList.add(new ShiftedCheckerboard("ShiftedCheckerboard"));
        // = . =
        // . = .
        // = . =
        listCommonGoalList.add(new CrossDirection(3, "Cross"));
        // = . . . .
        // . = . . .
        // . . = . .
        // . . . = .
        // . . . . =
        listCommonGoalList.add(new DiagonalDirection(5,true, "Diagonal"));
        // * . . . .
        // * * . . .
        // * * * . .
        // * * * * .
        // * * * * *
        listCommonGoalList.add(new DiagonalDirection(5,false, "Stair"));
    }

    /**
     * Gets random card.
     *
     * @return the random card
     */
    public static Set<CommonGoalCardStrategy> getRandomCards() {

        Random random = new Random();
        int firstRandom = random.nextInt(listCommonGoalList.size());
        int secondRandom = random.nextInt(listCommonGoalList.size());
        while (secondRandom == firstRandom)
        {
            secondRandom = random.nextInt(listCommonGoalList.size());
        }

        Set<CommonGoalCardStrategy> res = new HashSet<>();

        res.add(listCommonGoalList.get(firstRandom));
        res.add(listCommonGoalList.get(secondRandom));
        return Parser.fromJson(Parser.toJson(res, new TypeToken<Set<CommonGoalCardStrategy>>(){}.getType()), new TypeToken<Set<CommonGoalCardStrategy>>(){}.getType());
    }

    /**
     * <p>Setter for the field <code>numPlayers</code>.</p>
     *
     * @param numPlayers a int
     */
    public void setNumPlayers(int numPlayers) {
        this.points = new LinkedList<>();
        this.numPlayers = numPlayers;
         switch (this.numPlayers) {
             case 2 -> {
                 points.add(8);
                 points.add(4);
             }
             case 3 -> {
                 points.add(8);
                 points.add(6);
                 points.add(4);

             }
             case 4 -> {
                 points.add(8);
                 points.add(6);
                 points.add(4);
                 points.add(2);
             }
         }
    }

    /**
     * <p>Getter for the field <code>numPlayers</code>.</p>
     *
     * @return a int
     */
    public int getNumPlayers()
    {
        return numPlayers;
    }

    /**
     * Add player.
     *
     * @param player the player
     */
    public void addPlayer(Player player){
        completedMap.put(player.getUserName(), points.remove());
    }

    /**
     * Get players list.
     *
     * @return the list
     * @param player a {@link it.polimi.server.model.Player} object
     */
    public Integer getPlayer(Player player){
        if (completedMap.containsKey(player.getUserName()))
        {
            return completedMap.remove(player.getUserName());
        } else {
            return 0;
        }
    }

    /**
     * <p>isCompletedByPlayer.</p>
     *
     * @param player a {@link it.polimi.server.model.Player} object
     * @return a boolean
     */
    public boolean isCompletedByPlayer(Player player){
        return completedMap.containsKey(player.getUserName());
    }

    /**
     * <p>Getter for the field <code>listCommonGoalList</code>.</p>
     *
     * @return a {@link java.util.List} object
     */
    public static List<CommonGoalCardStrategy> getListCommonGoalList() {
        return listCommonGoalList;
    }

    private transient final List<Observer<Message>> observers = new ArrayList<>();

    /** {@inheritDoc} */
    @Override
    public void addObserver(Observer<Message> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void notify(Message move) {
        synchronized (observers) {
            for(Observer<Message> observer : observers){
                observer.update(move);
            }
        }
    }

    /**
     * <p>Getter for the field <code>points</code>.</p>
     *
     * @return a {@link java.util.Queue} object
     */
    public Queue<Integer> getPoints() {
        return points;
    }
}
