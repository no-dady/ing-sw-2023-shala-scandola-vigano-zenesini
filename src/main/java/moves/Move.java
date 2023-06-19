package moves;


import server.model.Game;

public abstract class Move {

    private final String nickName;
    private final String classname = "";

    public Move(String nickName) {
        this.nickName = nickName;
    }
    public String getNickName() {
        return nickName;
    }
    public abstract boolean canPerform(Game game);

    public String getName(){return this.classname;}


}