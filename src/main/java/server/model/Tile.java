package server.model;

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
    public String getImage(){return this.tileType.getImage();}

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
     * Gets pickable
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Tile t = (Tile) obj;

        return this.getTileType().equals(t.getTileType());
    }
}
