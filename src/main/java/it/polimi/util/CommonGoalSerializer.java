package it.polimi.util;

import com.google.gson.*;
import it.polimi.server.cgc.*;
import it.polimi.server.model.CommonGoalCardStrategy;

import java.lang.reflect.Type;
import java.util.HashMap;

public class CommonGoalSerializer  implements JsonSerializer<CommonGoalCardStrategy>, JsonDeserializer<CommonGoalCardStrategy> {
    private static final HashMap<String, Class> classNameMap = new HashMap<>();
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";


    static {
        classNameMap.put(CrossDirection.className, CrossDirection.class);
        classNameMap.put(MaxNTypes.className, MaxNTypes.class);
        classNameMap.put(DiagonalDirection.className, DiagonalDirection.class);
        classNameMap.put(SquareCheck.className, SquareCheck.class);
        classNameMap.put(StraightDirection.className, StraightDirection.class);
        classNameMap.put(ShiftedCheckerboard.className, ShiftedCheckerboard.class);
    }


    @Override
    public CommonGoalCardStrategy deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String className = json.getAsJsonObject().get(CLASSNAME).getAsString();
        Class c = classNameMap.get(className);
        return context.deserialize(json.getAsJsonObject().get(INSTANCE), c);
    }

    @Override
    public JsonElement serialize(CommonGoalCardStrategy cgc, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject ret = new JsonObject();
        ret.addProperty(CLASSNAME, cgc.getClassName());
        JsonElement e = context.serialize(cgc);
        ret.add(INSTANCE, e);
        return ret;
    }
}
