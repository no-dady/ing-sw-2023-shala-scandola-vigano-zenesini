package it.polimi.server.controller.actions;

import it.polimi.server.model.Game;

public interface Action {

    void performAction(Game game);

    boolean canPerformAction(Game game);

    String getNickName();
}