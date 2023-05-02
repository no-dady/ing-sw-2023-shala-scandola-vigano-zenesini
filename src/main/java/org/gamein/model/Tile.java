package org.gamein.model;

import java.io.Serializable;
import java.util.Optional;


/**
 * The type Tile.
 */
public class Tile implements Serializable {
    private Integer tileID;
    private TileType tileType;
    
    private boolean pickable;

    /**
     * Instantiates a new Tile.
     *
     * @param type the type
     * @param id   the id
     */
    public Tile(TileType type, Integer id) {
        this.tileType = type;
        this.tileID = id;
    }

    /**
     * Instantiates a new Tile.
     */
    public Tile(){
        this.tileType = new TileType("EMPTY", 0);
    }

    /**
     * Empty boolean.
     *
     * @return the boolean
     */
    public boolean Empty(){
        return this.getTileType().equals("EMPTY");
    }

    /**
     * Gets tile type.
     *
     * @return the tile type
     */
    public String getTileType()
    {
        return this.tileType.getKey();
    }

    /**
     * Gets tile id.
     *
     * @return the tile id
     */
    public Optional<Integer> getTileId()
    {
        return Optional.of(this.tileID);
    }

    /**
     * Is pickable boolean.
     *
     * @param board the board
     * @param x     the x
     * @param y     the y
     * @return the boolean
     */
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

    /**
     * Set pickable.
     *
     * @param pickable the pickable
     */
    public void setPickable(boolean pickable){
        this.pickable = pickable;
    }
}
