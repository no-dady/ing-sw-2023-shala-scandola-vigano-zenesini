package it.polimi.setup;

/**
 * <p>SetupAll class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class SetupAll implements Setup {
    /** Constant <code>className="SetupAll"</code> */
    public static final String className = "SetupAll";

    private final String nickname;

    /**
     * <p>Constructor for SetupAll.</p>
     *
     * @param nickname a {@link java.lang.String} object
     */
    public SetupAll(String nickname) {
        this.nickname = nickname;
    }

    /**
     * <p>Getter for the field <code>nickname</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getNickname()
    {
        return nickname;
    }

    /** {@inheritDoc} */
    @Override
    public String getParameter() {
        return nickname;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return className;
    }

}

