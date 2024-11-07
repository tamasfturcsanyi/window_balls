package game.model.serialization;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import game.model.shapes.CollisionCircle;
import game.model.shapes.CollisionRectangle;
import game.model.shapes.CollisionShape;

public class CollisionShapeTypeAdapter implements JsonSerializer<CollisionShape>, JsonDeserializer<CollisionShape>{
    @Override
    public CollisionShape deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        switch (type) {
            case "CollisionCircle":
                return context.deserialize(jsonObject, CollisionCircle.class);
            case "CollisionRectangle":
                return context.deserialize(jsonObject, CollisionRectangle.class);
            default:
                throw new JsonParseException("Unknown element type: " + type);
        }
    }
    
    @Override
    public JsonElement serialize(CollisionShape src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src, src.getClass()).getAsJsonObject();
        jsonObject.addProperty("type",src.getClass().getSimpleName());
        return jsonObject;
    }
}
