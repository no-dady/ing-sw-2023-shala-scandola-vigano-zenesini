package util;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.gamein.model.TileTypeRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static <T> T parseFromJson(String filePath, Class<T> cls) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        return new Gson().fromJson(reader, cls);
    }
    public static <T> T parseFromJson(String filePath, TypeToken<T> typeToken) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        return new Gson().fromJson(reader, typeToken.getType());
    }
}
