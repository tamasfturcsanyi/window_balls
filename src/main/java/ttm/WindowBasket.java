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
        Vector2D starterForce = new Vector2D(300,-1000);
        

        Ball ball2 = new Ball(200, 600,10);
        Ball ball3 = new Ball(300, 600,10);

        this.add(ball2);
        this.add(ball3);

        //Line line = new Line(100,200,700,100);
        ttm.Rectangle rect = new ttm.Rectangle(new Vector2D(100,300),new Vector2D(300,100),Color.CYAN);

        gp.addVisual(rect);
        ball2.addForce(starterForce);
        ball3.addForce(starterForce);
        
        int i = 0;
        while(true){
            cycle();
            if(i % 10000000 == 0){
                System.out.println("ball 1 velocity: " + ball2.getVelocity().toString());
                System.out.println("ball 1 position: " + ball2.getPos().toString());
                System.out.println();

                System.out.println("ball 2 velocity: " + ball3.getVelocity().toString());
                System.out.println("ball 2 position: " + ball3.getPos().toString());
                System.out.println();


            }
            ++i;

        }
    }
}
