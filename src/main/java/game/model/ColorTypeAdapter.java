package game.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.awt.Color;
import java.io.IOException;

public class ColorTypeAdapter extends TypeAdapter<Color> {
    @Override
    public void write(JsonWriter out, Color color) throws IOException {
        if (color == null) {
            out.nullValue();
            return;
        }
        // Serialize Color as an RGB string (e.g., "#RRGGBB")
        out.value(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
    }

    @Override
    public Color read(JsonReader in) throws IOException {
        String colorString = in.nextString();
        return Color.decode(colorString);
    }
}
