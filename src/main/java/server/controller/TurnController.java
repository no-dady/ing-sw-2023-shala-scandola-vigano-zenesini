package server.controller;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import server.controller.actions.Action;
import server.exceptions.InvalidColumnException;
import server.exceptions.InvalidTileException;
import server.model.Board;
import server.model.Bookshelf;
import server.model.Tile;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

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

public void SelectTile(Board board, Bookshelf bookshelf) throws InvalidTileException, InvalidColumnException {
        int more = 0;
        ArrayList<Tile> selected = null;
        while(more <= 2){
            Scanner coordinates = new Scanner(System.in);
            int x = parseInt(coordinates.nextLine());//devono essere passate in gui quindi dalla view
            int y = parseInt(coordinates.nextLine());//
            more = parseInt(coordinates.nextLine());//in test se si vogliono prendere meno di 3 tiles si può cambiare il valore di more
            if(board.getTile(x,y).IsPickable(board,x,y)){
                selected.add(SelectedTileControl(board,x,y));
                more++;
            }
            else{
                System.out.println("Tile's not pickable"); //lancia errore senza però togliere la possibilità di scegliere la tile
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

    public void SelectColumn() throws NotImplementedException {
        throw new NotImplementedException("Todo");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

