package moves;

import server.model.Game;

public class MoveSelectColum extends Move {
    int selectedColumn;
    private final String classname= "MoveSelectColumn";

    public MoveSelectColum(String nickName) {
        super(nickName);
    }

    @Override
    public String getName() {
        return classname;
    }

    @Override
    public boolean canPerform(Game game) {
        return true;
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }
}
