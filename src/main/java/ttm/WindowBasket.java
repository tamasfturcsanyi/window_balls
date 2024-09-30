package ttm;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

public class WindowBasket {
    JFrame window;

    Rectangle windowBounds;

    WindowBasket(){
        windowBounds = new Rectangle(0,0,500,300);

        window = new JFrame("window_basket");
        window.setBounds(windowBounds);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);

    }

    void run(){
        Circle circle = new Circle(100,100,20);
        //window.add()
        while(true){
            windowBounds = window.getBounds();
            circle.calculateVisual(windowBounds);
        }
    }
}
