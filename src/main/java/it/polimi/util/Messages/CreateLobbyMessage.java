package it.polimi.util.Messages;

import it.polimi.client.Client;

/**
 * <p>CreateLobbyMessage class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class CreateLobbyMessage implements Message {
    /** Constant <code>className="CreateLobbyMessage"</code> */
    public static final String className = "CreateLobbyMessage";
        //private final ClientInterface clientInterface;
        private final String nickName;
        private final int numberOfPlayer;
        /**
         * <p>Constructor for CreateLobbyMessage.</p>
         *
         * @param nickName a {@link java.lang.String} object
         * @param numberOfPlayer a int
         */
        public CreateLobbyMessage(String nickName, int numberOfPlayer)
        {
            //this.clientInterface = clientInterface;
            this.nickName = nickName;
            this.numberOfPlayer = numberOfPlayer;
        }

    /**
     * <p>Getter for the field <code>nickName</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getNickName()
        {
            return nickName;
        }

        /**
         * <p>Getter for the field <code>numberOfPlayer</code>.</p>
         *
         * @return a int
         */
        public int getNumberOfPlayer()
        {
            return numberOfPlayer;
        }

        /** {@inheritDoc} */
        @Override
        public void handleMessage(Client client)
        {
            System.out.println("Prova");
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
