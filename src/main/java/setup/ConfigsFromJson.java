package setup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.controller.BoardConfig;
import server.model.PersonalGoalCard;
import server.model.TileTypeRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ConfigsFromJson {

    public static List<PersonalGoalCard> getpgcList(String jsonPath) throws RuntimeException, IOException{
        Reader reader = Files.newBufferedReader(Paths.get(jsonPath));// "src/main/resources/json/personalgoalcards.json"
        return new Gson().fromJson(reader, new TypeToken<List<PersonalGoalCard>>() {}.getType());
    }

    public static Map<String, TileTypeRecord> getTileType(String jsonPath) throws RuntimeException, IOException {
        Reader reader = Files.newBufferedReader(Paths.get(jsonPath)); // "src/main/resources/json/tiletypes_config.json"
        return new Gson().fromJson(reader, new TypeToken<Map<String, TileTypeRecord>>() {}.getType());
    }

    public static BoardConfig getBoardConfig(String jsonPath) throws RuntimeException, IOException{
        Reader reader = Files.newBufferedReader(Paths.get(jsonPath)); // "src/main/resources/json/board_config.json"
        return new Gson().fromJson(reader, BoardConfig.class);
    }

    public static String getBoardArt(String jsonPath) throws RuntimeException, IOException {
        FileReader reader = new FileReader(jsonPath);//"src/main/resources/json/board_art.json"
        return new Gson().fromJson(reader, String.class);
    }

    public static String getBookshelfArt(String jsonPath) throws RuntimeException, IOException {
        FileReader reader = new FileReader(jsonPath); // "src/main/resources/json/bookshelf_art.json"
        return new Gson().fromJson(reader, String.class);
    }
}
