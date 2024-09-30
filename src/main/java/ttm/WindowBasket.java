package ttm;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class WindowBasket {
    GraphicsPanel gp;

    JFrame window;
    Rectangle windowBounds;

    PhysicksWorld world;


    WindowBasket(){
        windowBounds = new Rectangle(0,0,1000,800);
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
        //Circle circle = new Circle(100,100,200);
        Ball ball = new Ball(300, 100, 50, 1);
        gp.addVisual(ball);
        world.addBody(ball);
        //gp.addShape(circle.getVisual());

        //window.add()
        while(true){
            cycle();
        }
    }
}
