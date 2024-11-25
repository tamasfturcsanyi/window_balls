package game.model.serialization;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import game.model.physicsbodies.Ball;
import game.model.physicsbodies.Brick;
import game.model.physicsbodies.PhysicsBody;
import game.model.physicsbodies.Pole;
import game.model.physicsbodies.Wall;

import java.lang.reflect.Type;

public class PhysicsBodyTypeAdapter implements JsonSerializer<PhysicsBody>, JsonDeserializer<PhysicsBody>{
    @Override
    public PhysicsBody deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        switch (type) {
            case "Ball":
                return context.deserialize(jsonObject, Ball.class);
            case "Wall":
                return context.deserialize(jsonObject, Wall.class);
            case "Brick":
                return context.deserialize(jsonObject, Brick.class);
            case "Pole":
                return context.deserialize(jsonObject, Pole.class);
            default:
                throw new JsonParseException("Unknown element type: " + type);
        }
    }
    
    @Override
    public JsonElement serialize(PhysicsBody src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src, src.getClass()).getAsJsonObject();
        jsonObject.addProperty("type",src.getClass().getSimpleName());
        return jsonObject;
    }
}
