package game.engine;

import javax.swing.JFrame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.graphics.GraphicsPanel;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Simulation {
    GraphicsPanel gp;

    JFrame window;

    PhysicksWorld world;


    public Simulation(){
        world = new PhysicksWorld();
        gp = new GraphicsPanel();
        window = new JFrame(world.getTitle());
        window.setBounds(world.windowBounds);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.add(gp);
    }

    void initGraphicsPanel(){
        for (Actor actor : world.bodies) {
            gp.addVisual(actor);
        }
    }

    public void run(){    
        //world = loadWorld("src/main/resources/Preset_2.json");//NOSONAR
        world.preset2();

        window.setBounds(world.windowBounds);
        initGraphicsPanel();
        //saveWorld();//NOSONAR

        //main loop, only exits if window is closed
        while(true){
            cycle();
        }
    }

    void physicksUpdate(){
        world.update();
    }

    void cycle(){
        world.setWindowBounds(window.getBounds());
        physicksUpdate();
        gp.updateElements(world.windowBounds);
        window.repaint();
    }

    void saveWorld(){
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .registerTypeAdapter(Actor.class, new ActorTypeAdapter())
            .create();

        String worldJSON = gson.toJson(world);
        try {
            FileWriter writer = new FileWriter("src/main/resources/" + world.title + ".json");
            writer.write(worldJSON);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    PhysicksWorld loadWorld(String path){
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .registerTypeAdapter(Actor.class, new ActorTypeAdapter())
            .create();
        PhysicksWorld newWorld = null;
        try {
            BufferedReader reader = new  BufferedReader( new FileReader(path));
            StringBuilder worldJSON = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                worldJSON.append(line);
            }
            newWorld = gson.fromJson(worldJSON.toString(), PhysicksWorld.class);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();     
        }
        return newWorld;
    }
}
