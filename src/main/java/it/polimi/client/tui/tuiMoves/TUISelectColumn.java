package it.polimi.client.tui.tuiMoves;

import it.polimi.moves.Move;
import it.polimi.moves.MoveSelectColumn;
import it.polimi.server.model.Game;

import java.util.Scanner;

/**
 * <p>TUISelectColumn class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class TUISelectColumn implements TUIMoveInterface {
    public final MoveSelectColumn move;

    /**
     * <p>Constructor for TUISelectColumn.</p>
     *
     * @param nickName a {@link java.lang.String} object
     * @param lobbyId a int
     */
    public TUISelectColumn(String nickName, int lobbyId){
        this.move = new MoveSelectColumn(nickName, lobbyId);
    }
    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public boolean canPerform(Game game) {
       return move.canPerform(game);
    }

}
