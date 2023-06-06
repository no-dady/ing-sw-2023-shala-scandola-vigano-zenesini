package setup;

public abstract class Setup {
    private final String playerName;
    private final int numOfPlayers;

    public Setup(String playerName, int numOfPlayers) {
        this.playerName=playerName;
        this.numOfPlayers=numOfPlayers;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getNumOfPlayers(){
        return numOfPlayers;
    }

}