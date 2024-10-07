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
        Vector2D starterForce1 = new Vector2D(600,-2000);
        Vector2D starterForce2 = new Vector2D(-600,-2000);
        

        Ball ball1 = new Ball(200, 600,10);
        ball1.setColor(Color.BLUE);
        Ball ball2 = new Ball(700, 600,10);
        ball2.setColor(Color.RED);

        this.add(ball1);
        this.add(ball2);

        //ttm.Rectangle rect = new ttm.Rectangle(new Vector2D(100,300),new Vector2D(300,100),Color.CYAN);
        //gp.addVisual(rect);
        ball1.addForce(starterForce1);
        ball2.addForce(starterForce2);
        
        int i = 0;
        while(true){
            cycle();
            if(i % 10000000 == 0){
                System.out.println("ball 1 velocity: " + ball1.getVelocity().toString());
                System.out.println("ball 1 position: " + ball1.getPos().toString());
                System.out.println();

                System.out.println("ball 2 velocity: " + ball2.getVelocity().toString());
                System.out.println("ball 2 position: " + ball2.getPos().toString());
                System.out.println();


            }
            ++i;

        }
    }
}
