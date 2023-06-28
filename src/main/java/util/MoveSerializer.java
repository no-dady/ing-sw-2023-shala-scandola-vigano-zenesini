package util;

import com.google.gson.*;
import moves.Move;
import moves.MoveSelectColumn;
import moves.MoveSelectTiles;

import java.lang.reflect.Type;
import java.util.HashMap;

public class MoveSerializer implements JsonSerializer<Move>, JsonDeserializer<Move> {
    private static final HashMap<String, Class> classNameMap = new HashMap<>();
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";


    static {
        classNameMap.put(MoveSelectTiles.className, MoveSelectTiles.class);
        classNameMap.put(MoveSelectColumn.className, MoveSelectColumn.class);
    }


    @Override
    public Move deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String className = json.getAsJsonObject().get(CLASSNAME).getAsString();
        Class c = classNameMap.get(className);
        return context.deserialize(json.getAsJsonObject().get(INSTANCE), c);
    }


    @Override
    public JsonElement serialize(Move move, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject ret = new JsonObject();
        ret.addProperty(CLASSNAME, move.getClassName());
        System.out.println(move.getClassName());
        JsonElement e = context.serialize(move);
        ret.add(INSTANCE, e);
        System.out.println(ret);
        return ret;
    }
}
