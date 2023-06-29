package it.polimi.moves;


import it.polimi.server.model.Game;

public interface Move {

    String getNickName();

    int getLobbyId();
    boolean canPerform(Game game);

    abstract String getClassName();
}