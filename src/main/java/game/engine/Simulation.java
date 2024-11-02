package game.engine;

import javax.swing.JFrame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.graphics.GraphicsPanel;

import java.awt.Color;
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
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
            .setPrettyPrinting()
            .create();

        String worldJSON = gson.toJson(world);
        try {
            FileWriter writer = new FileWriter("src/main/resources/" + world.title + ".json");
            writer.write(worldJSON);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    void initGraphicsPanel(){
        for (Actor actor : world.bodies) {
            gp.addVisual(actor);
        }
    }

    public void run(){    
        world.preset1();
        world.wallInit();

        initGraphicsPanel();
        saveWorld();

        //main loop, only exits if window is closed
        while(true){
            cycle();
        }
    }


}
