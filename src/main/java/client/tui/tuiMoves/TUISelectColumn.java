package client.tui.tuiMoves;

import moves.Move;
import moves.MoveSelectColum;
import server.model.Game;

import java.util.Scanner;

public class TUISelectColumn implements TUIMoveInterface {
    public final MoveSelectColum move;

    public TUISelectColumn(String nickName){
        this.move = new MoveSelectColum(nickName);
    }
    @Override
    public Move updateCLI(Game game, Scanner stdin) {
        int selectedColumn;
        String confirmed;
        do {
            do {
                System.out.println("[go ahead and choose the colum in which you want to put the tile/es and press ENTER]");
                selectedColumn = stdin.nextInt();
                System.out.println("[are you okey with this selection?" + selectedColumn + "Y/N then Enter]");
                confirmed = stdin.nextLine();
            } while (!confirmed.equals("Y"));
            move.setSelectedColumn(selectedColumn);
        }while (!canPerform(game));
        return move;
    }

    @Override
    public boolean canPerform(Game game) {
       return move.canPerform(game);
    }

}
