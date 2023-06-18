package util.Messages;

import client.network.ClientInterface;

public class CreateLobbyMessage implements Message {
        //private final ClientInterface clientInterface;
        private final String nickName;
        private final int numberOfPlayer;
        public CreateLobbyMessage(String nickName, int numberOfPlayer)
        {
            //this.clientInterface = clientInterface;
            this.nickName = nickName;
            this.numberOfPlayer = numberOfPlayer;
        }
        //This could be used as handleMessage
        //public ClientInterface getClientInterface()
        //{
        //    return clientInterface;
        //}

        public String getNickName()
        {
            return nickName;
        }

        public int getNumberOfPlayer()
        {
            return numberOfPlayer;
        }

        @Override
        public void handleMessage(ClientInterface clientInterface)
        {
            System.out.println("Prova");
        }

        public String getName()
        {
            return "Boh";
        }
}
