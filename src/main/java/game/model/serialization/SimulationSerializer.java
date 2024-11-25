package game.model.serialization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.Color;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.model.Simulation;
import game.model.physicsbodies.PhysicsBody;
import game.model.shapes.CollisionShape;


/**
 * SimulationSerializer is a utility class that provides static methods to save and load a Simulation object to and from a JSON file.
 * It uses the Gson library to serialize and deserialize the Simulation object.
 */
public class SimulationSerializer {
    // Private constructor to hide the implicit public one
    private SimulationSerializer() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    /**
     * Serializes the given Simulation object to a JSON file.
     * The JSON file is saved in the "src/main/resources/jsons/" directory with the title of the world as the filename.
     * 
     * @param world the Simulation object to be serialized and saved
     */
    public static void saveWorld(Simulation world){
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(PhysicsBody.class, new PhysicsBodyTypeAdapter())
            .registerTypeAdapter(CollisionShape.class,new CollisionShapeTypeAdapter())
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .create();

        String worldJSON = gson.toJson(world);
        try {
            FileWriter writer = new FileWriter("src/main/resources/jsons/" + world.getTitle() + ".json");
            writer.write(worldJSON);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a Simulation object from a JSON file at the specified path.
     * The method uses Gson to deserialize the JSON content into a Simulation object.
     * It also registers custom type adapters for CollisionShape, Color, and PhysicsBody.
     *
     * @param path the file path to the JSON file containing the simulation data
     * @return a Simulation object initialized with the data from the JSON file
     */
    public static Simulation loadWorld(String path){
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(CollisionShape.class,new CollisionShapeTypeAdapter())
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .registerTypeAdapter(PhysicsBody.class, new PhysicsBodyTypeAdapter())
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
