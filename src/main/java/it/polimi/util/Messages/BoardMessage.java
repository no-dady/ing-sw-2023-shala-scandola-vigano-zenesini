package it.polimi.util.Messages;

import it.polimi.client.Client;
import it.polimi.server.model.Board;

/**
 * <p>BoardMessage class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class BoardMessage implements Message{
    /** Constant <code>className="BoardMessage"</code> */
    public static final String className = "BoardMessage";
    private final Board board;

    /**
     * <p>Constructor for BoardMessage.</p>
     *
     * @param board a {@link it.polimi.server.model.Board} object
     */
    public BoardMessage(Board board)
    {
        this.board = board;
    }

    /** {@inheritDoc} */
    @Override
    public void handleMessage(Client client) {
        client.getGame().setBoard(board);
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
