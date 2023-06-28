package moves;


import server.model.Game;

public abstract class Move {
    private final String nickName;
    private final int lobbyId;
    public Move(String nickName, int lobbyId) {
        this.nickName = nickName;
        this.lobbyId = lobbyId;
    }
    public String getNickName() {
        return nickName;
    }

    public int getLobbyId()
    {
        return lobbyId;
    }
    public abstract boolean canPerform(Game game);

    public abstract String getClassName();
}