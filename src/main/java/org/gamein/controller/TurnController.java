package org.gamein.controller;

import org.gamein.Exceptions.InvalidTileException;
import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;
import org.gamein.Exceptions.*;

import java.util.*;

import jdk.jshell.spi.ExecutionControl.*;

import static java.lang.Integer.parseInt;

/**
 * The type Turn controller.
 */
@Deprecated
public class TurnController implements Observer {

    private Tile SelectedTileControl(Board board, int x, int y) throws InvalidTileException {
        if(board.getTile(x,y).IsPickable(board,x,y)){
            board.removeTile(x,y); //setting the tile as not pickable and removing it from the board
            return board.getTile(x,y);
        }
        else{
            throw new InvalidTileException (x + y + "is not a valid tile");
        }
    }

    private boolean SelectedColumnControl(Bookshelf bookshelf, int x, ArrayList<Tile> tileList) throws InvalidColumnException{
        if(bookshelf.getTilePerCol(x,bookshelf)+tileList.size()<6){ //the condition verifies that you have enough space
            bookshelf.setSlots(x,tileList); //add the selected list of tile to the bookshelf
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

