package server.controller.actions;

import server.model.Game;

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
