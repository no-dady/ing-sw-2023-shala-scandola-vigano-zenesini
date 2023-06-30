package it.polimi.client.tui.tuiMoves;

import it.polimi.moves.Move;
import it.polimi.moves.MoveSelectTiles;
import it.polimi.server.model.Bookshelf;
import it.polimi.server.model.Game;

import java.util.Scanner;

/**
 * <p>TUISelectTiles class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class TUISelectTiles implements TUIMoveInterface {
    public final MoveSelectTiles move;
    /**
     * <p>Constructor for TUISelectTiles.</p>
     *
     * @param nickName a {@link java.lang.String} object
     * @param lobbyId a int
     */
    public TUISelectTiles(String nickName, int lobbyId){
        this.move = new MoveSelectTiles(nickName, lobbyId);
    }
    /** {@inheritDoc} */
    @Override
    public Move updateCLI(Game game, Scanner stdin) {
        String selectedTiles;
        String confirmed;
        int max;
        do {
            do {
                max = 0;
                System.out.println("[it's your turn, choose the tiles you want (max three) choose amongst the colored ones, the grey ones cannot be picked to select them type their coordinates separated by spaces and then press ENTER, you'll know the ones you can grab thanks to their color not being grey!]");
                selectedTiles = stdin.nextLine();
                System.out.println("[are you okey with this selection? " + selectedTiles + " Y/N then Enter]");
                confirmed = stdin.nextLine();

                for (int i = 0; i < Bookshelf.getCols(); i++) {
                    max = Math.max(game.getPlayerByNickname(move.getNickName()).getBookshelf().getEmptyTilesColumn(i), max);
                }
                if (selectedTiles.split(" ").length > max) {
                    System.out.println("[You cannot pick all these tiles because you don't have enough space in your bookshelf]");
                }
                if (selectedTiles.split(" ").length > 3) {
                    System.out.println("[You can only pick 3 tiles]");
                }
            } while (!confirmed.equals("Y") && !(selectedTiles.split(" ").length > max) && !(selectedTiles.split(" ").length > 3));
            move.setSelectedTiles(selectedTiles);
        }while (!canPerform(game));
        return move;
    }

    /** {@inheritDoc} */
    @Override
    public boolean canPerform(Game game) {
        return move.canPerform(game);
    }

}
