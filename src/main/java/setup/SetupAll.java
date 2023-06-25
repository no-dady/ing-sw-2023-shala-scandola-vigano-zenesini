package setup;

public class SetupAll implements Setup {
    public static final String className = "SetupAll";

    private final String nickname;

    public SetupAll(String nickname) {
        this.nickname = nickname;
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

