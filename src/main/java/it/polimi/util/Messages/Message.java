package it.polimi.util.Messages;

import it.polimi.client.Client;

/**
 * <p>Message interface.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public interface Message {

    /**
     * <p>handleMessage.</p>
     *
     * @param client a {@link it.polimi.client.Client} object
     */
    void handleMessage(Client client);

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object
     */
    String getName();
}
