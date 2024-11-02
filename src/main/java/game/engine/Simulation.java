package game.engine;

import javax.swing.JFrame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.graphics.GraphicsPanel;

import java.awt.Color;
import java.io.FileWriter;

public class Simulation {
    String title = "Simulation";

    final int WINDOW_WIDTH;
    final int WINDOW_HEIGHT;

    GraphicsPanel gp;

    JFrame window;
    java.awt.Rectangle windowBounds;

    PhysicksWorld world;

    Wall[] walls;

    public Simulation(Vector2D windowDimensions){
        WINDOW_WIDTH = (int)windowDimensions.x;
        WINDOW_HEIGHT = (int)windowDimensions.y;
        windowBounds = new java.awt.Rectangle(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        gp = new GraphicsPanel();
        world = new PhysicksWorld();
        window = new JFrame(title);
        window.setBounds(windowBounds);
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.add(gp);

        walls = new Wall[4];

        walls[0] = new Wall(Direction.NORTH,50000);
        walls[1] = new Wall(Direction.EAST,50000);
        walls[2] = new Wall(Direction.SOUTH,50000);
        walls[3] = new Wall(Direction.WEST,50000);

        for (Wall wall : walls) {
            this.add(wall);
        }
    }

    void add(Actor actor){
        gp.addVisual(actor);
        world.addBody(actor);
    }

    void physicksUpdate(){
        world.update();
    }

    void cycle(){
        windowBounds = window.getBounds();
        physicksUpdate();
        gp.updateElements(windowBounds);
        window.repaint();
    }

    public void run(){    
        for(int i = 0; i < 10;++i){
            Ball ball = new Ball(20+i * 10, 200,10,1,false,1,Color.BLUE);
            add(ball);
        }

        Brick brick = new Brick(new Vector2D(300,500),new Vector2D(500,15));

        //adds to physicksWorld, and graphicsPanel
        this.add(brick);

        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Color.class, new ColorTypeAdapter())
                .create();

        String worldJSON = gson.toJson(world);
        try {
            FileWriter writer = new FileWriter("src/main/resources/world.json");
            writer.write(worldJSON);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        //main loop, only exits if window is closed
        while(true){
            cycle();
        }
    }


}
