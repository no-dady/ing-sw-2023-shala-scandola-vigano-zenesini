package server.view;

import util.Messages.InitialMessage;
import util.Messages.LastMessage;
import util.Messages.Message;
import observer.Observable;
import observer.Observer;
import server.controller.actions.Action;
import server.model.Game;
import server.model.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class View implements Observable<Action>, Observer<Message> {
    private final Player player;
    private boolean offline = false;

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
        //notify(new MoveAutoPlay(player.getUserName()));
    }

    protected View(Player player) {
        this.player = player;
    }

    protected Player getPlayer() {
        return player;
    }

    void handleMove(Action a) {
        notify(a);
    }
    

    private transient final List<Observer<Action>> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer<Action> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }
    @Override
    public void notify(Action action) {
        synchronized (observers) {
            for(Observer<Action> observer : observers){
                observer.update(action);
            }
        }
    }

    public void sendInitialMessage(Game game, String lobbyName) {
        if(game.getPlayerIndex(this.player) == -1) return;

        int id = this.player.getUserId();
        // TODO: initial message needs to send board and lobbyname to clients
        this.update(new InitialMessage(game, id, player.getBookshelf(), player.getPersonalGoalCard()));
        this.update(new LastMessage());
    }

}