package ttm;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class WindowBasket {

    static int WINDOW_HEIGHT = 1300;
    static int WINDOW_WIDTH = 1500;

    GraphicsPanel gp;

    JFrame window;
    Rectangle windowBounds;

    PhysicksWorld world;


    WindowBasket(){
        windowBounds = new Rectangle(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        gp = new GraphicsPanel();
        world = new PhysicksWorld();


        window = new JFrame("window_basket");
        window.setBounds(windowBounds);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.add(gp);
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

    void run(){
        Vector2D starterForce1 = new Vector2D(-0,-10);
        Vector2D starterForce2 = new Vector2D(10,-1);
        
        //Wall wall1 = new Wall(Direction.NORTH,100);
        //Wall wall2 = new Wall(Direction.EAST,100);
        //Wall wall3 = new Wall(Direction.SOUTH,100);
        //Wall wall4 = new Wall(Direction.WEST,100);

        Brick brick = new Brick(new Vector2D(300,600),new Vector2D(1000,200));

        
        Ball ball1 = new Ball(400, 500,40,1,false,1,Color.BLUE);
        Ball ball2 = new Ball(500, 100,40,10,false,1,Color.RED);

        Ball ball3 = new Ball(800, 300,40,1,false,1,Color.PINK);

        this.add(ball1);
        this.add(ball2);
        this.add(ball3);

        this.add(brick);

        //this.add(wall1);
        //this.add(wall2);
        //this.add(wall3);
        //this.add(wall4);

        ttm.Rectangle rect = new ttm.Rectangle(new Vector2D(100,300),new Vector2D(300,100),Color.CYAN);
        //gp.addVisual(rect);
        //ball1.addForce(starterForce1.stretch(10000));
        //ball2.addForce(starterForce2.stretch(1000));
        //
        int i = 0;
        while(true){
            cycle();
            if(i % 10000000 == 0){
                //System.out.println(wall1.getPos());
                //System.out.println(wall2.getPos());
                //System.out.println(wall3.getPos());
                //System.out.println(wall4.getPos());
                //System.out.println();

                //System.out.println("ball 1 velocity: " + ball1.getVelocity().toString());
                //System.out.println("ball 1 position: " + ball1.getPos().toString());
                //System.out.println();
//
                //System.out.println("ball 2 velocity: " + ball2.getVelocity().toString());
                //System.out.println("ball 2 position: " + ball2.getPos().toString());
                //System.out.println();


            }
            ++i;

        }
    }
}
