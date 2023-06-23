package util;

import com.google.gson.*;
import util.Messages.*;

import java.lang.reflect.Type;
import java.util.HashMap;

public class MessageSerializer implements JsonSerializer<Message>, JsonDeserializer<Message> {

    private static final HashMap<String, Class> classNameMap = new HashMap<>();
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    static {
        classNameMap.put(GameMessage.className, GameMessage.class);
        classNameMap.put(BookshelfMessage.className, BookshelfMessage.class);
        classNameMap.put(PlayerMessage.className, PlayerMessage.class);
        classNameMap.put(LastMessage.className, LastMessage.class);
        classNameMap.put(ConfirmMessage.className, ConfirmMessage.class);
        classNameMap.put(ErrorMessage.className, ErrorMessage.class);
        classNameMap.put(ConnectionMessage.className, ConnectionMessage.class);
        classNameMap.put(ReconnectMessage.className, ReconnectMessage.class);
        classNameMap.put(DisconnectMessage.className,DisconnectMessage.class);
        classNameMap.put(JoinedMessage.className, JoinedMessage.class);
    }
    @Override
    public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String className = json.getAsJsonObject().get(CLASSNAME).getAsString();
        Class c = classNameMap.get(className);
        return context.deserialize(json.getAsJsonObject().get(INSTANCE), c);
    }

    @Override
    public JsonElement serialize(Message message, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject ret = new JsonObject();
        ret.addProperty(CLASSNAME, message.getName());
        JsonElement e = context.serialize(message);
        ret.add(INSTANCE, e);
        return ret;
    }
}
