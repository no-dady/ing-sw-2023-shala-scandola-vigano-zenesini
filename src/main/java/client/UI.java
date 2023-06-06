package client;

import network.ClientInterface;
import network.Messages.Message;


public interface UI {

    void update();

    void setActive();


    void printConnectionMessage(Message message);

    void setConnectionType (ClientInterface type);
    void setNickname (String nickname);
    void setPlayerNumber (int playerNumber);
    void setSelectedTiles (String selectedTiles);
    void setSelectedColum (int selectedColum);
    void getInfoAboutOtherPlayers (String playerNickname);


}
