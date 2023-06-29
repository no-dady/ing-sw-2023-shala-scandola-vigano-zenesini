package it.polimi.server.controller;

import it.polimi.observer.Observer;
import it.polimi.server.controller.actions.Action;
import it.polimi.server.model.Game;

public class Controller implements Observer<Action> {
    private final Game game;

    public Controller(Game game) {
        this.game = game;
    }

    @Override
    public synchronized void update(Action x) {
        if(x.canPerformAction(game)) {
            x.performAction(game);
            game.lastMessage();
        }else{
            System.out.println("Move invalid");
            //x.getPlayer().setErrorMessage();
            game.errorMessage(x.getNickName());
        }
    }
}
