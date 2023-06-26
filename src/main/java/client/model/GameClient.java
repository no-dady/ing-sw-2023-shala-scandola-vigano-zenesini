package client.model;

import client.Client;
import server.model.Bookshelf;
import server.model.Game;
import server.model.Player;

import java.util.ArrayList;

public class GameClient extends Game {
    private transient int myID;
    // private transient Bookshelf bookshelf;

    public boolean isMyTurn() {
        return (12getCurrPlayer() == null) ? false : getCurrPlayer().getUserId() == this.myID;
    }

    public Player getMe() {
        return super.getPlayerFromId(myID);
    }

    public int getMyId() {
        return myID;
    }

    // public Bookshelf getBookshelf() {
    //     return bookshelf;
    // }

    public void setMyId(int id) {
        this.myID = id;
    }

}
