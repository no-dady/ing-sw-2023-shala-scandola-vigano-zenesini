package moves;


import server.model.Game;

public abstract class Move {

    private final String nickName;

    public Move(String nickName) {
        this.nickName = nickName;
    }
    public String getNickName() {
        return nickName;
    }
    public abstract boolean canPerform(Game game);

    public abstract String getName();


}