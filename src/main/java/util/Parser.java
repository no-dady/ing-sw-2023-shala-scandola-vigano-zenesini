package util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import setup.Setup;
import util.Messages.Message;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {
    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Message.class, new MessageSerializer());
        builder.registerTypeAdapter(Setup.class, new SetupSerializer());


        gson = builder.create();
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static String toJson(Object src, Type typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }

    public static <T> T parseFromJson(String filePath, Class<T> cls) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        return new Gson().fromJson(reader, cls);
    }
    public static <T> T parseFromJson(String filePath, TypeToken<T> typeToken) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        return new Gson().fromJson(reader, typeToken.getType());
    }
}
