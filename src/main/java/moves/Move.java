package moves;


import server.model.Game;

public interface Move {

    public String getNickName();

    public int getLobbyId();
    public boolean canPerform(Game game);

    public abstract String getClassName();
}