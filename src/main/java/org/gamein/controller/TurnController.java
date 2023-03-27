package org.gamein.controller;

import org.gamein.model.Board;
import org.gamein.model.Bookshelf;
import org.gamein.model.Tile;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import jdk.jshell.spi.ExecutionControl.*;

@Deprecated
public class TurnController implements Observer {
    boolean stop;

    private void SelectedTileControl(Board board, int x, int y) throws InvalidTileException {
        if(board.getTile(x,y).IsPickable()){
        }
        else{
            throw new InvalidTileException (x + y + "is not a valid tile");
        }
    }

    private void SelectedColumnControl(Bookshelf bookshelf, int x, ArrayList<Tile> tileList) throws NotImplementedException{
        throw new NotImplementedException("Todo");
    }

    public void SelectTile() throws NotImplementedException {
        throw new NotImplementedException("Todo");
    }

    public void SelectColumn() throws NotImplementedException {
        throw new NotImplementedException("Todo");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

