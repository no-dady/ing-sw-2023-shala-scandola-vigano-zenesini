package server.model;

import setup.ConfigsFromJson;

import java.util.*;


public class TileType {
    private static Map<String, TileTypeRecord> tileMap;
    private String key;
    private String color;
    private String sign;
    private String image;

    public TileType() {
        tileMap = new HashMap<>();
        try {
            ConfigsFromJson.getTileType("src/main/resources/json/tiletypes_config.json");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public TileType(String tiletype, int index) {
        TileType temp = new TileType();
        this.key = tiletype;
        this.color = tileMap.get(tiletype).color;
        this.sign = tileMap.get(tiletype).sign;
        this.image = tileMap.get(tiletype).images.get(index);
    }

    public static Map<String, TileTypeRecord> getTileMap() {
        return tileMap;
    }

    public String getKey(){
        return this.key;
    }

    public String getColor() {
        return this.color;
    }

    public String getImage() {
        return this.image;
    }

    public String getSign() {
        return this.sign;
    }

    public static Set<String> values(){
    return tileMap.keySet();
    }
}
