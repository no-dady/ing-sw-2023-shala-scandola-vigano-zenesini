package setup;

public class SetupFirst extends Setup{
    private String classname = "SetupFirst";

    public SetupFirst(String numOfPlayers){
            super(numOfPlayers);
        }
    public String getName() {
        return classname;
    }

}