package game.model.serialization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.Color;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.model.Simulation;
import game.model.physicksbodies.PhysicksBody;
import game.model.shapes.CollisionShape;

public class SimulationSerializer {
    public static void saveWorld(Simulation world){
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(PhysicksBody.class, new PhysicksBodyTypeAdapter())
            .registerTypeAdapter(CollisionShape.class,new CollisionShapeTypeAdapter())
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .create();

        String worldJSON = gson.toJson(world);
        try {
            FileWriter writer = new FileWriter("src/main/resources/" + world.getTitle() + ".json");
            writer.write(worldJSON);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static Simulation loadWorld(String path){
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(CollisionShape.class,new CollisionShapeTypeAdapter())
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .registerTypeAdapter(PhysicksBody.class, new PhysicksBodyTypeAdapter())
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
        newWorld.wallInit();
        return newWorld;
    }
}
