package it.polimi.server.model;

import java.io.Serializable;


/**
 * The type Tile.
 *
 * @author daniel
 * @version $Id: $Id
 */
public class Tile implements Serializable {
    private Integer tileID;
    private final TileType tileType;
    
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
        this.tileType = new TileType();
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
     * <p>getImage.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getImage(){return this.tileType.getImage();}

    /**
     * Gets pickable
     *
     * @return the boolean pickable
     */
    public boolean isPickable() {
        return pickable;
    }

    /**
     * Set pickable.
     *
     * @param pickable the pickable
     */
    public void setPickable(boolean pickable){
        this.pickable = pickable;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;

        if (obj == null || !(obj instanceof Tile) ||  this.tileType.getKey().equals("EMPTY") || ((Tile) obj).getTileType().equals("EMPTY")) {
            return false;
        }


        Tile t = (Tile) obj;

        return this.getTileType().equals(t.getTileType());
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return TileType.getTileMap().keySet().stream().toList().indexOf(this.getTileType());
    }
}
