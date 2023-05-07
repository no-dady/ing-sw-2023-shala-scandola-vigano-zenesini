package server.model;

import setup.ConfigsFromJson;

import java.util.*;


/**
 * The type Tile type.
 */
public class TileType {
    private static Map<String, TileTypeRecord> tileMap;
    private String key;
    private String color;
    private String sign;
    private String image;

    /**
     * Instantiates a new Tile type.
     */
    public TileType() {
        tileMap = new HashMap<>();
        try {
            ConfigsFromJson.getTileType("src/main/resources/json/tiletypes_config.json");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Instantiates a new Tile type.
     *
     * @param tiletype the tiletype
     * @param index    the index
     */
    public TileType(String tiletype, int index) {
        TileType temp = new TileType();
        this.key = tiletype;
        this.color = tileMap.get(tiletype).color;
        this.sign = tileMap.get(tiletype).sign;
        this.image = tileMap.get(tiletype).images.get(index);
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
