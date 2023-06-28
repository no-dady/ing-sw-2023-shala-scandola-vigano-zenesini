package client.tui.tuiMoves;

import moves.Move;
import moves.MoveSelectColumn;
import server.model.Game;

import java.util.Scanner;

public class TUISelectColumn implements TUIMoveInterface {
    public final MoveSelectColumn move;

    public TUISelectColumn(String nickName, int lobbyId){
        this.move = new MoveSelectColumn(nickName, lobbyId);
    }
    @Override
    public Move updateCLI(Game game, Scanner stdin) {
        int selectedColumn;
        String confirmed;
        do {
            do {
                System.out.println("[go ahead and choose the colum in which you want to put the tile/es and press ENTER]");
                selectedColumn = Integer.valueOf(stdin.nextLine());
                System.out.println("[are you okey with this selection? " + selectedColumn + " press Y/N then ENTER]");
                confirmed = stdin.nextLine();
                selectedColumn--;
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
