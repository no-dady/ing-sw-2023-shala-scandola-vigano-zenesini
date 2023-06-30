package it.polimi.server.model;

import it.polimi.setup.ConfigsFromJson;
import it.polimi.util.Parser;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;


/**
 * The type Tile type.
 *
 * @author daniel
 * @version $Id: $Id
 */
public class TileType implements Serializable {
    private static Map<String, TileTypeRecord> tileMap = new HashMap<>();
    private final String key;
    private final String color;
    private final String sign;
    private final String image;

    static {
        try {
            tileMap = ConfigsFromJson.getTileType(Parser.getResourcePath("json/tiletypes_config.json"));
        } catch (RuntimeException | IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * <p>Constructor for TileType.</p>
     */
    public TileType() {
        this.key = "EMPTY";
        this.color = tileMap.get(this.key).color;
        this.sign = tileMap.get(this.key).sign;
        this.image = this.key.charAt(0) + this.key.substring(1).toLowerCase() + tileMap.get(this.key).images.get(0) + ".png";
    }

    /**
     * <p>Constructor for TileType.</p>
     *
     * @param tileType a {@link java.lang.String} object
     * @param index a int
     */
    public TileType(String tileType, int index) {
        this.key = tileType;
        this.color = tileMap.get(tileType).color;
        this.sign = tileMap.get(tileType).sign;
        this.image = tileType.charAt(0) + tileType.substring(1).toLowerCase() + tileMap.get(tileType).images.get(index) + ".png";
    }

    /**
     * Gets tile map.
     *
     * @return the tile map
     */
    public static Map<String, TileTypeRecord> getTileMap() {
        return tileMap;
    }

    /**
     * Get key string.
     *
     * @return the string
     */
    public String getKey(){
        return this.key;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Gets sign.
     *
     * @return the sign
     */
    public String getSign() {
        return this.sign;
    }

    /**
     * Values set.
     *
     * @return the set
     */
    public static Set<String> values(){
        return tileMap.keySet();
    }

}
