package it.polimi.util.Messages;

import it.polimi.client.Client;

import java.util.Set;

/**
 * <p>ConnectionMessage class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class ConnectionMessage implements Message {
    /** Constant <code>className="ConnectionAcceptedMessage"</code> */
    public static final String className = "ConnectionAcceptedMessage";
    private final Set<String> playersName;
    private final String playerName;

    /**
     * <p>Constructor for ConnectionMessage.</p>
     *
     * @param playersName a {@link java.util.Set} object
     * @param playerName a {@link java.lang.String} object
     */
    public ConnectionMessage(Set<String> playersName, String playerName)
    {
        this.playersName = playersName;
        this.playerName = playerName;
    }
    /** {@inheritDoc} */
    @Override
    public void handleMessage(Client client) {
        //it.polimi.client.getUI().printConnectionMessage(this);
    }

    /**
     * <p>Getter for the field <code>playersName</code>.</p>
     *
     * @return a {@link java.util.Set} object
     */
    public Set<String> getPlayersName() {
        return playersName;
    }

    /**
     * <p>Getter for the field <code>playerName</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getPlayerName() {
        return playerName;
    }
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return className;
    }
    /** {@inheritDoc} */
    @Override
    public String toString() {
        String x = "";
        x += "AGGIORNAMENTO: "+playerName+" si è unito alla stanza!\n";
        x += "Giocatori attualmente collegati\n";
        int i = 1;
        for (String name: playersName){
            x += "\t" + i + ". " + name + "\n";
            i++;
        }
        return x;
    }
}
