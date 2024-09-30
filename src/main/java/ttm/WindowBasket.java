package ttm;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

public class WindowBasket {
    GraphicsPanel gp;

    JFrame window;

    Rectangle windowBounds;

    WindowBasket(){
        windowBounds = new Rectangle(0,0,500,300);
        gp = new GraphicsPanel();


        window = new JFrame("window_basket");
        window.setBounds(windowBounds);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);

    }

    void run(){
        Circle circle = new Circle(100,100,200);
        gp.addShape(circle.getVisual());

        //window.add()
        while(true){
            windowBounds = window.getBounds();
            circle.calculateVisual(windowBounds);
            window.add(gp);
            window.repaint();
        }
    }
}
