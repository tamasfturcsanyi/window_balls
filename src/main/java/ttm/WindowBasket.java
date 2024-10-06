package ttm;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class WindowBasket {

    static int WINDOW_HEIGHT = 300;
    static int WINDOW_WIDTH = 500;

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

    }

    void physicksUpdate(){
        world.update();
    }

    void cycle(){
        windowBounds = window.getBounds();
        physicksUpdate();
        gp.updateElements(windowBounds);
        window.add(gp);
        window.repaint();
    }

    void run(){
        Ball ball = new Ball(100, 300, 20, 1);
        gp.addVisual(ball);
        world.addBody(ball);
        ball.addForce(new Vector2D(1000,-10000));
        while(true){
            cycle();
        }
    }
}
