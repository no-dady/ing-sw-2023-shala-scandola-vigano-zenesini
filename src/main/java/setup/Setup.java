package setup;

public abstract class Setup {
    private final String parameter;
    private final String classname = "";

    public Setup(String parameter) {
        this.parameter=parameter;
    }

    public String getParameter() {
        return parameter;
    }

    public String getName() {
        return classname;
    }

}