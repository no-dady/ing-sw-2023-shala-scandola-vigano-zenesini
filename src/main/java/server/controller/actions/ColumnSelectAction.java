package server.controller.actions;

import server.model.Game;

public class ColumnSelectAction implements Action{
    @Override
    public void performAction(Game game) {

    }

    @Override
    public boolean canPerformAction(Game game) {
        return false;
    }

    @Override
    public int getIdPlayer() {
        return 0;
    }
}
