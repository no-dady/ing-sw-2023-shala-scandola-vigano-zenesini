package it.polimi.setup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.server.controller.BoardConfig;
import it.polimi.server.model.PersonalGoalCard;
import it.polimi.server.model.TileTypeRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ConfigsFromJson {

    public static List<PersonalGoalCard> getpgcList(String jsonPath) throws RuntimeException, IOException{
        Reader reader = Files.newBufferedReader(Paths.get(jsonPath));// Parser.getFilePath("personalgoalcards.json"
        return new Gson().fromJson(reader, new TypeToken<List<PersonalGoalCard>>() {}.getType());
    }

    public static Map<String, TileTypeRecord> getTileType(String jsonPath) throws RuntimeException, IOException {
        Reader reader = Files.newBufferedReader(Paths.get(jsonPath)); // Parser.getFilePath("tiletypes_config.json"
        return new Gson().fromJson(reader, new TypeToken<Map<String, TileTypeRecord>>() {}.getType());
    }

    public static BoardConfig getBoardConfig(String jsonPath) throws RuntimeException, IOException{
        Reader reader = Files.newBufferedReader(Paths.get(jsonPath)); // Parser.getFilePath("board_config.json"
        return new Gson().fromJson(reader, BoardConfig.class);
    }

    public static String getBoardArt(String jsonPath) throws RuntimeException, IOException {
        FileReader reader = new FileReader(jsonPath);//Parser.getFilePath("board_art.json"
        return new Gson().fromJson(reader, String.class);
    }
    public static String getBoardAndBookshelfArt(String jsonPath) throws RuntimeException, IOException {
        FileReader reader = new FileReader(jsonPath);//Parser.getFilePath("board_bookshelf_pgc_art.json"
        return new Gson().fromJson(reader, String.class);
    }

    public static String getBookshelfArt(String jsonPath) throws RuntimeException, IOException {
        FileReader reader = new FileReader(jsonPath); // Parser.getFilePath("bookshelf_art.json"
        return new Gson().fromJson(reader, String.class);
    }
    public static String getArt(String jsonPath) throws RuntimeException, IOException {
        FileReader reader = new FileReader(jsonPath);//Parser.getFilePath("PGCArt.json"
        return new Gson().fromJson(reader, String.class);
    }
}
