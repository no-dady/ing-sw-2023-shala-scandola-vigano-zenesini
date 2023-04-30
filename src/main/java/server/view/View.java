package server.view;

import network.Message;
import observer.Observable;
import observer.Observer;
import server.controller.actions.Action;
import server.model.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class View implements Observable<Action>, Observer<Message>{
    private transient final List<Observer<Action>> observers = new ArrayList<>();
    private final Player player;
    private boolean offline = false;

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
        notify(new MoveAutoPlay(player.getID()));
    }

    protected View(Player player) {
        this.player = player;
    }
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

}