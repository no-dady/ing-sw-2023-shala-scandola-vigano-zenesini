package it.polimi.util.Messages;

import it.polimi.client.Client;
import it.polimi.client.network.State;

/**
 * <p>StateMessage class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class StateMessage implements Message {

    /** Constant <code>className="StateMessage"</code> */
    public static final String className = "StateMessage";
    private final State stateToSend;

    /**
     * <p>Constructor for StateMessage.</p>
     *
     * @param state a {@link it.polimi.client.network.State} object
     */
    public StateMessage(State state)
    {
        this.stateToSend = state;
    }

    /** {@inheritDoc} */
    @Override
    public void handleMessage(Client client)
    {
        client.setState(stateToSend);
    }

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getName()
    {
        return className;
    }
}
