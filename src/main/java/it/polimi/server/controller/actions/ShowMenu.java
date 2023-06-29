package it.polimi.server.controller.actions;

import it.polimi.server.model.Game;


public class ShowMenu implements Action{


    @Override
    public void performAction(Game game) {

    }

    @Override
    public boolean canPerformAction(Game game) {
        return true; // a player can always quit even if it's not his turn
    }

    @Override
    public String getNickName() {
        return null;
    }

}
