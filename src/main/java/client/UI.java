package client;

import network.Message;

public interface UI {

    void update();

    void setActive();

    void printConnectionMessage(Message message);
    void setNickname (String nickname);
    void setPlayerNumber (int playerNumber);
    void setSelectedTiles (String selectedTiles);
    void setSelectedColum (int selectedColum);
    void getInfoAboutOtherPlayers (String playerNickname);


}
