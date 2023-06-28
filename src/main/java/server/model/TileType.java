package server.model;

import setup.ConfigsFromJson;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;


/**
 * The type Tile type.
 */
public class TileType implements Serializable {
    private static Map<String, TileTypeRecord> tileMap = new HashMap<>();
    private String key;
    private String color;
    private String sign;
    private String image;

    static {
        try {
            tileMap = ConfigsFromJson.getTileType("src/main/resources/json/tiletypes_config.json");
        } catch (RuntimeException | IOException e) {
            System.err.println(e.getMessage());
        }
    }

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
