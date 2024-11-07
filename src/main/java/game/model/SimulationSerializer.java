package game.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.Color;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SimulationSerializer {
     void saveWorld(Simulation world){
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .create();

        String worldJSON = gson.toJson(world);
        try {
            FileWriter writer = new FileWriter("src/main/resources/" + "sim" + ".json");
            writer.write(worldJSON);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Simulation loadWorld(String path){
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .create();
        Simulation newWorld = null;
        try {
            BufferedReader reader = new  BufferedReader( new FileReader(path));
            StringBuilder worldJSON = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                worldJSON.append(line);
            }
            newWorld = gson.fromJson(worldJSON.toString(), Simulation.class);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();     
        }
        return newWorld;
    }
}
