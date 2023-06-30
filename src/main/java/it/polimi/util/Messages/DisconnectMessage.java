package it.polimi.util.Messages;

import it.polimi.client.Client;

import java.util.Set;

/**
 * <p>DisconnectMessage class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class DisconnectMessage extends ConnectionMessage implements Message {
    /** Constant <code>className="DisconnectMessage"</code> */
    public static final String className = "DisconnectMessage";
    /**
     * <p>Constructor for DisconnectMessage.</p>
     *
     * @param playersName a {@link java.util.Set} object
     * @param playerName a {@link java.lang.String} object
     */
    public DisconnectMessage(Set<String> playersName, String playerName) {
        super(playersName, playerName);
    }
    /** {@inheritDoc} */
    @Override
    public void handleMessage(Client client) {
        //it.polimi.client.getUI().printConnectionMessage(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        String x="";
        x+="AGGIORNAMENTO: "+super.getPlayerName()+" si è disconnesso dalla stanza!\n";
        x+="Giocatori attualmente collegati\n";
        int i=1;
        for (String name: super.getPlayersName()){
            x+="\t"+i+". "+name+"\n";
            i++;
        }
        return x;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return className;
    }
}
