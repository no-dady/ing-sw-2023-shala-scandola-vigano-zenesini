package server.controller.actions;

import server.model.Game;

public interface Action {

    void performAction(Game game);


    boolean canPerformAction(Game game);

    int getIdPlayer();
}