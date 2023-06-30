package it.polimi.setup;

/**
 * <p>SetupFirst class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class SetupFirst implements Setup {
    /** Constant <code>className="SetupFirst"</code> */
    public static final String className = "SetupFirst";

    private final String nickname;
    private final int numOfPlayers;

    /**
     * <p>Constructor for SetupFirst.</p>
     *
     * @param nickname a {@link java.lang.String} object
     * @param numOfPlayers a {@link java.lang.String} object
     */
    public SetupFirst(String nickname, String numOfPlayers){
        this.nickname = nickname;
        this.numOfPlayers = Integer.parseInt(numOfPlayers);
    }

    /** {@inheritDoc} */
    @Override
    public String getParameter() {
        return nickname;
    }

    /**
     * <p>Getter for the field <code>numOfPlayers</code>.</p>
     *
     * @return a int
     */
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return className;
    }

}
