package server.model;

import java.io.Serializable;
import java.util.Optional;


public class Tile implements Serializable {
    private Integer tileID;
    private TileType tileType;
    
    private boolean pickable;

    public Tile(TileType type, Integer id) {
        this.tileType = type;
        this.tileID = id;
    }

    public Tile(){
        this.tileType = new TileType("EMPTY", 0);
    }
    public boolean Empty(){
        return this.getTileType().equals("EMPTY");
    }
    public String getTileType()
    {
        return this.tileType.getKey();
    }

    public Optional<Integer> getTileId()
    {
        return Optional.of(this.tileID);
    }

    public boolean IsPickable(Board board, int x, int y) {
        int count = 0;
        if((board.getTile(x-1,y).Empty())){
            count=count+1;
        }
        if((board.getTile(x+1,y).Empty())){
            count=count+1;
        }
        if((board.getTile(x,y-1).Empty())){
            count=count+1;
        }
        if((board.getTile(x,y+1).Empty())){
            count=count+1;
        }
        if( count >= 2) {
            return true;
        }
        else {
            return false;
        }

    }
    
    public void setPickable(boolean pickable){
        this.pickable = pickable;
    }


}
