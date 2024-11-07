package game.model.serialization;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import game.model.physicksbodies.PhysicksBody;

public class PhysicksBodyTypeAdapter extends TypeAdapter<PhysicksBody> {
    @Override
    public void write(JsonWriter out, PhysicksBody value) throws IOException {
    }
}
