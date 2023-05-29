package server.controller.actions;

import server.model.Game;

import java.util.Scanner;

public class ShowMenu implements Action{




    @Override
    public boolean canPerformAction(Game game) {
        return true; // a player can always quit even if it's not his turn
    }

    @Override
    public int getIdPlayer() {
        return 0;
    }
}
