package org.gamein.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.gamein.view.ConsoleColors;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class TileType {
    //CAT(ConsoleColors.GREEN_BACKGROUND, "CAT"), TROPHY(ConsoleColors.CYAN_BACKGROUND, "TRO"), BOOK(ConsoleColors.WHITE_BACKGROUND_BRIGHT, "BOO"), TOYS(ConsoleColors.RED_BACKGROUND_BRIGHT, "TOY"), FRAMES(ConsoleColors.BLUE_BACKGROUND, "FRA"), FLOWERS(ConsoleColors.PURPLE_BACKGROUND, "FLO"), EMPTY(ConsoleColors.RESET, "   ");
    private static Map<String, TileTypeRecord> tileMap;
    private String key;
    private String color;
    private String sign;
    private String image;

    public TileType() {
        tileMap = new HashMap<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/json/tiletypes_config.json"));
            tileMap = new Gson().fromJson(reader, new TypeToken<Map<String, TileTypeRecord>>() {}.getType());
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
