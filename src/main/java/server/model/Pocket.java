package server.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pocket implements Serializable {
    private final ArrayList<Tile> tileList;

    public Pocket() {
        this.tileList = new ArrayList<Tile>();
    }

    public Pocket(ArrayList<Tile> tiles) {

        this.tileList = tiles;
    }

    public int getLeft() {
        return this.tileList.size();
    }

    public ArrayList<Tile> popTiles(int n){
        ArrayList<Tile> poppedTiles = new ArrayList<>(tileList.subList(0,n));
        tileList.subList(0,n).clear();
        return poppedTiles;
    }

}
