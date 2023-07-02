package it.polimi.util;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.moves.Move;
import it.polimi.server.model.CommonGoalCardStrategy;
import it.polimi.setup.Setup;
import it.polimi.util.Messages.Message;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * <p>Parser class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class Parser {
    private static final Gson gson;
    private static final ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            return field.getAnnotation(Exclude.class) != null;
        }
    };


    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Message.class, new MessageSerializer());
        builder.registerTypeAdapter(Setup.class, new SetupSerializer());
        builder.registerTypeAdapter(Move.class, new MoveSerializer());
        builder.registerTypeAdapter(CommonGoalCardStrategy.class, new CommonGoalSerializer());
        builder.addSerializationExclusionStrategy(strategy);

        gson = builder.create();
    }

    /**
     * <p>getResourcePath.</p>
     *
     * @param filename a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     * @throws java.io.IOException if any.
     */
    public static String getResourcePath(String filename) throws IOException, NullPointerException {
        return "/" + filename;

        // try {
        //     File file = Paths.get(Objects.requireNonNull(resource).toURI()).toFile();
        //     return file.getAbsolutePath();
        // } catch (Exception e) {
        //     throw new IOException(e);
        // }

    }
    /**
     * <p>fromJson.</p>
     *
     * @param json a {@link java.lang.String} object
     * @param classOfT a {@link java.lang.Class} object
     * @param <T> a T class
     * @return a T object
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
    /**
     * <p>fromJson.</p>
     *
     * @param json a {@link java.lang.String} object
     * @param typeOfT a {@link java.lang.reflect.Type} object
     * @param <T> a T class
     * @return a T object
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    /**
     * <p>toJson.</p>
     *
     * @param src a {@link java.lang.Object} object
     * @param typeOfSrc a {@link java.lang.reflect.Type} object
     * @return a {@link java.lang.String} object
     */
    public static String toJson(Object src, Type typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }

    /**
     * <p>parseFromJson.</p>
     *
     * @param filePath a {@link java.lang.String} object
     * @param cls a {@link java.lang.Class} object
     * @param <T> a T class
     * @return a T object
     * @throws java.io.IOException if any.
     */
    public static <T> T parseFromJson(String filePath, Class<T> cls) throws IOException {
        String reader = new String(Parser.class.getResourceAsStream(filePath).readAllBytes(), "UTF-8");
        // Reader reader = Files.newBufferedReader(Paths.get(filePath));
        return new Gson().fromJson(reader, cls);
    }
    /**
     * <p>parseFromJson.</p>
     *
     * @param filePath a {@link java.lang.String} object
     * @param typeToken a {@link com.google.gson.reflect.TypeToken} object
     * @param <T> a T class
     * @return a T object
     * @throws java.io.IOException if any.
     */
    public static <T> T parseFromJson(String filePath, TypeToken<T> typeToken) throws IOException {
        String reader = new String(Parser.class.getResourceAsStream(filePath).readAllBytes(), "UTF-8");
        return new Gson().fromJson(reader, typeToken.getType());
    }
}
