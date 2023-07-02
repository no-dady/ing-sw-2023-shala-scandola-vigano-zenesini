package it.polimi.setup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.server.controller.BoardConfig;
import it.polimi.server.model.PersonalGoalCard;
import it.polimi.server.model.TileTypeRecord;
import it.polimi.util.Parser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * <p>ConfigsFromJson class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class ConfigsFromJson {

    /**
     * <p>getpgcList.</p>
     *
     * @param jsonPath a {@link java.lang.String} object
     * @return a {@link java.util.List} object
     * @throws java.lang.RuntimeException if any.
     * @throws java.io.IOException if any.
     */
    public static List<PersonalGoalCard> getpgcList(String jsonPath) throws RuntimeException, IOException{
        // Reader reader = Files.newBufferedReader(Paths.get(jsonPath));// Parser.getFilePath("personalgoalcards.json"
        // return new Gson().fromJson(reader, new TypeToken<List<PersonalGoalCard>>() {}.getType());
        return Parser.parseFromJson(jsonPath, new TypeToken<List<PersonalGoalCard>>() {});
    }

    /**
     * <p>getTileType.</p>
     *
     * @param jsonPath a {@link java.lang.String} object
     * @return a {@link java.util.Map} object
     * @throws java.lang.RuntimeException if any.
     * @throws java.io.IOException if any.
     */
    public static Map<String, TileTypeRecord> getTileType(String jsonPath) throws RuntimeException, IOException {
        //Reader reader = Files.newBufferedReader(Paths.get(jsonPath)); // Parser.getFilePath("tiletypes_config.json"
        //return new Gson().fromJson(reader, new TypeToken<Map<String, TileTypeRecord>>() {}.getType());
        return Parser.parseFromJson(jsonPath, new TypeToken<Map<String, TileTypeRecord>>() {});
    }

    /**
     * <p>getBoardConfig.</p>
     *
     * @param jsonPath a {@link java.lang.String} object
     * @return a {@link it.polimi.server.controller.BoardConfig} object
     * @throws java.lang.RuntimeException if any.
     * @throws java.io.IOException if any.
     */
    public static BoardConfig getBoardConfig(String jsonPath) throws RuntimeException, IOException{
        // Reader reader = Files.newBufferedReader(Paths.get(jsonPath)); // Parser.getFilePath("board_config.json"
        // return new Gson().fromJson(reader, BoardConfig.class);
        return Parser.parseFromJson(jsonPath, BoardConfig.class);
    }

    /**
     * <p>getBoardArt.</p>
     *
     * @param jsonPath a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     * @throws java.lang.RuntimeException if any.
     * @throws java.io.IOException if any.
     */
    public static String getBoardArt(String jsonPath) throws RuntimeException, IOException {
        // FileReader reader = new FileReader(jsonPath);//Parser.getFilePath("board_art.json"
        // return new Gson().fromJson(reader, String.class);
        return Parser.parseFromJson(jsonPath, String.class);
    }
    /**
     * <p>getBoardAndBookshelfArt.</p>
     *
     * @param jsonPath a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     * @throws java.lang.RuntimeException if any.
     * @throws java.io.IOException if any.
     */
    public static String getBoardAndBookshelfArt(String jsonPath) throws RuntimeException, IOException {
        // FileReader reader = new FileReader(jsonPath);//Parser.getFilePath("board_bookshelf_pgc_art.json"
        // return new Gson().fromJson(reader, String.class);
        return Parser.parseFromJson(jsonPath, String.class);
    }

    /**
     * <p>getBookshelfArt.</p>
     *
     * @param jsonPath a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     * @throws java.lang.RuntimeException if any.
     * @throws java.io.IOException if any.
     */
    public static String getBookshelfArt(String jsonPath) throws RuntimeException, IOException {
        // FileReader reader = new FileReader(jsonPath); // Parser.getFilePath("bookshelf_art.json"
        // return new Gson().fromJson(reader, String.class);
        return Parser.parseFromJson(jsonPath, String.class);
    }
    /**
     * <p>getArt.</p>
     *
     * @param jsonPath a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     * @throws java.lang.RuntimeException if any.
     * @throws java.io.IOException if any.
     */
    public static String getArt(String jsonPath) throws RuntimeException, IOException {
        // FileReader reader = new FileReader(jsonPath);//Parser.getFilePath("PGCArt.json"
        // return new Gson().fromJson(reader, String.class);
        return Parser.parseFromJson(jsonPath, String.class);
    }
}
