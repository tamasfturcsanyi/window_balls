package ttm;

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
        Vector2D starterForce = new Vector2D(3000,-10000);
        

        Ball ball2 = new Ball(200, 300, 10, 1);
        Ball ball3 = new Ball(300, 300, 10, 1);

        this.add(ball2);
        this.add(ball3);
        ball2.addForce(starterForce);
        ball3.addForce(starterForce);
        
        int i = 0;
        while(true){
            cycle();

            ++i;
            if(i % 10000000 == 0){
                //Ball ball = new Ball(100, 300, 20, 1);
                //this.add(ball);
                //ball.addForce(new Vector2D(3000,-10000));
            }
        }
    }
}
