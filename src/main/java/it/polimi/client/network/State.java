package it.polimi.client.network;

/**
 * <p>State class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public enum State {
    WAIT,
    SETUPFIRST,
    SETUP,
    WAITINGINLOBBY,
    SETTINGNICKNAME,
    SETUPAGAIN,
    WAITINGFORMYTURN,
    MYTURN,
    WAITINGFORRESPONSE,
    WAITINGFORGAMESTART,
    INQUEUE,
    GAMEENDED
}
