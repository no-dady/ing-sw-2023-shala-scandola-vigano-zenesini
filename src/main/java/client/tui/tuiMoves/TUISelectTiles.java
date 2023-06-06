package client.tui.tuiMoves;

import moves.Move;
import moves.MoveSelectColum;
import moves.MoveSelectTiles;
import server.model.Game;

import java.util.Scanner;

public class TUISelectTiles implements TUIInterface{
    public MoveSelectTiles move;
    public TUISelectTiles(String nickName){
        this.move = new MoveSelectTiles(nickName);
    }
    @Override
    public Move updateCLI(Game game, Scanner stdin) {
        String selectedTiles;
        String confirmed;
            do {
                System.out.println("it's your turn, choose the tiles you want to select by typing their coordinates separated by spaces and then press ENTER, you'll know the ones you can grab thanks to their color not being grey!");
                selectedTiles = stdin.nextLine();
                System.out.println("are you okey with this selection?" + selectedTiles + "Y/N then Enter");
                confirmed = stdin.nextLine();
            }while (!confirmed.equals("Y"));
    move.setSelectedTiles(selectedTiles);
    return move;
    }

    @Override
    public boolean canPerform(Game game) {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }
}
