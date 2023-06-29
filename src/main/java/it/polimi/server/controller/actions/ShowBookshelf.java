package it.polimi.server.controller.actions;

import it.polimi.server.model.Game;

public class ShowBookshelf implements Action{
    @Override
    public void performAction(Game game) {

    }

    @Override
    public boolean canPerformAction(Game game) {
        return false;
    }

    @Override
    public String getNickName() {
        return null;
    }

}
