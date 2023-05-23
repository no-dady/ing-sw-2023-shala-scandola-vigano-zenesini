package moves;

import server.model.Game;

public class MoveSelectColum extends Move {
    int selectedColumn;

    public MoveSelectColum(String nickName) {
        super(nickName);
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
