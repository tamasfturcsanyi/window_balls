package game;

import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JFrame;

import game.graphics.GraphicsPanel;
import game.engine.*;
import game.graphics.*;

public class WindowBasket {

    static final int WINDOW_HEIGHT = 700;
    static final int WINDOW_WIDTH = 1000;

    GraphicsPanel gp;

    JFrame window;
    Rectangle windowBounds;

    PhysicksWorld world;

    Wall[] walls;


    public WindowBasket(){
        windowBounds = new Rectangle(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        gp = new GraphicsPanel();
        world = new PhysicksWorld();


        window = new JFrame("window_basket");
        window.setBounds(windowBounds);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        //example balls
        //Ball ball1 = new Ball(400, 200,20,1,false,1,Color.BLUE);
        //Ball ball2 = new Ball(500, 100,40,10,false,1,Color.RED);
        //Ball ball3 = new Ball(800, 300,40,2,false,1,Color.PINK);

        for(int i = 0; i < 10;++i){
            Ball ball = new Ball(20+i * 10, 200,10,1,false,1,Color.BLUE);
            add(ball);
        }

        Brick brick = new Brick(new Vector2D(300,500),new Vector2D(500,15));

        //adds to physicksWorld, and graphicsPanel
        //this.add(ball2);
        //this.add(ball3);
        //this.add(ball1);
        this.add(brick);

        //main loop, only exits if window is closed
        while(true){
            cycle();
        }
    }
}
