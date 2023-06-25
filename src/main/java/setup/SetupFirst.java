package setup;

public class SetupFirst implements Setup {
    public static final String className = "SetupFirst";

    private final String nickname;
    private final int numOfPlayers;

    public SetupFirst(String nickname, String numOfPlayers){
        this.nickname = nickname;
        this.numOfPlayers = Integer.parseInt(numOfPlayers);
    }

    @Override
    public String getParameter() {
        return nickname;
    }

    @Override
    public String getName() {
        return className;
    }

}