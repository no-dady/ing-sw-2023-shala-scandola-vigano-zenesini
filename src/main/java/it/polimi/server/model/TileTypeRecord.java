package it.polimi.server.model;

import java.io.Serializable;
import java.util.List;

/**
 * The type Tile type record.
 */
public class TileTypeRecord implements Serializable {
    /**
     * The Color.
     */
    public String color;
    /**
     * The Sign.
     */
    public String sign;
    /**
     * The Images.
     */
    public List<String> images;

}
