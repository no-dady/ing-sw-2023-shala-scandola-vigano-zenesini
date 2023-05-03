package server.controller;

import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;
import server.Exceptions.*;

import java.util.*;

import jdk.jshell.spi.ExecutionControl.*;

import static java.lang.Integer.parseInt;

/**
 * The type Turn controller.
 */
@Deprecated
public class TurnController extends Controller {

private Tile SelectedTileControl(Board board, int x, int y) throws InvalidTileException {
        if(board.getTile(x,y).isPickable()){
            board.removeTile(x,y); //imposta la Tile come non pickable oppure la rimuove del tutto dalla board
            return board.getTile(x,y);
        }
        else{
            throw new InvalidTileException (x + y + "is not a valid tile");
        }
    }

    private boolean SelectedColumnControl(Bookshelf bookshelf, int x, ArrayList<Tile> tileList) throws InvalidColumnException {
        if(bookshelf.getTilePerCol(x,bookshelf)+tileList.size()<6){ //controlla che ci sia spazio
            bookshelf.setSlots(x,tileList); //aggiunge alla bookshelf
            return true;
        }
        else{
            throw new InvalidColumnException("Column is not available");
        }
    }

    /**
     * Select tile.
     *
     * @param board     the board
     * @param bookshelf the bookshelf
     * @throws InvalidTileException   the invalid tile exception
     * @throws InvalidColumnException the invalid column exception
     */
    public void SelectTile(Board board, Bookshelf bookshelf) throws InvalidTileException, InvalidColumnException {
        int more = 0;
        ArrayList<Tile> selected = null;
        while(more <= 2){
            Scanner coordinates = new Scanner(System.in);
            int x = parseInt(coordinates.nextLine());//to be passed by the gui
            int y = parseInt(coordinates.nextLine());//
            more = parseInt(coordinates.nextLine());//in a test case the more parameters can be modified
            if(board.getTile(x,y).IsPickable(board,x,y)){
                selected.add(SelectedTileControl(board,x,y));
                more++;
            }
            else{
                System.out.println("Tile's not pickable"); //throws an error even if you'll still be able to pick another tile
            }
        }
        Scanner col = new Scanner(System.in);
        System.out.println("Insert column");
        int index = parseInt(col.nextLine());
        assert selected != null;
        while(!SelectedColumnControl(bookshelf, index, selected)){
            System.out.println("Insert column");
            index = parseInt(col.nextLine());
        }
    }

    /**
     * Select column.
     *
     * @throws NotImplementedException the not implemented exception
     */
    public void SelectColumn() throws NotImplementedException { //useless
        throw new NotImplementedException("Todo");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

