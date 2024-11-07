package game.model.serialization;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import game.model.physicksbodies.Ball;
import game.model.physicksbodies.Brick;
import game.model.physicksbodies.PhysicksBody;
import game.model.physicksbodies.Pole;
import game.model.physicksbodies.Wall;

public class PhysicksBodyTypeAdapter implements JsonSerializer<PhysicksBody>, JsonDeserializer<PhysicksBody>{
    @Override
    public PhysicksBody deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)throws JsonParseException {
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
    public JsonElement serialize(PhysicksBody src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src, src.getClass()).getAsJsonObject();
        jsonObject.addProperty("type",src.getClass().getSimpleName());
        return jsonObject;
    }
}
