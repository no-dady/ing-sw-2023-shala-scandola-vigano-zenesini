package it.polimi.util;

import com.google.gson.*;
import it.polimi.setup.Setup;
import it.polimi.setup.SetupAll;
import it.polimi.setup.SetupFirst;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * <p>SetupSerializer class.</p>
 *
 * @author daniel
 * @version $Id: $Id
 */
public class SetupSerializer implements JsonSerializer<Setup>, JsonDeserializer<Setup> {

    private static final HashMap<String, Class> classNameMap = new HashMap<>();
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    static {
        classNameMap.put(SetupFirst.className, SetupFirst.class);
        classNameMap.put(SetupAll.className, SetupAll.class);
    }

    /** {@inheritDoc} */
    @Override
    public Setup deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String className = json.getAsJsonObject().get(CLASSNAME).getAsString();
        Class c = classNameMap.get(className);
        return context.deserialize(json.getAsJsonObject().get(INSTANCE), c);
    }

    /** {@inheritDoc} */
    @Override
    public JsonElement serialize(Setup setup, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject ret = new JsonObject();
        ret.addProperty(CLASSNAME, setup.getName());
        JsonElement e = context.serialize(setup);
        ret.add(INSTANCE, e);
        return ret;
    }
}
