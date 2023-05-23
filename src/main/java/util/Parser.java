package util;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import server.controller.actions.Action;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    public static <T> T parseFromJson(String filePath, Class<T> cls) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        return new Gson().fromJson(reader, cls);
    }
    public static <T> T parseFromJson(String filePath, TypeToken<T> typeToken) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        return new Gson().fromJson(reader, typeToken.getType());
    }
    public static String parseAction(Action action) {
        return new Gson().toJson(action, Action.class);
    }
}
