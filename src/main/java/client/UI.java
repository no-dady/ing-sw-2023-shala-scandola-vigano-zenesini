package client;

import network.ConnectionType;
import network.Message;

import java.util.List;

public interface UI {

    void update();

    void setActive();

    void printConnectionMessage(Message message);
    void setConnectionType (ConnectionType type);
    void setNickname (String nickname);
    void setPlayerNumber (int playerNumber);
    void setSelectedTiles (String selectedTiles);
    void setSelectedColum (int selectedColum);
    void getInfoAboutOtherPlayers (String playerNickname);


}
