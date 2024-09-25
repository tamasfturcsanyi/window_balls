package ttm;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class App {
    public static void main(String[] args) throws Exception {
        JFrame window = new JFrame("window_basket");
        Rectangle rect = new Rectangle(100,100,500,300);


        window.setBounds(rect);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setVisible(true);
        window.setBackground(Color.RED);

        ShapePanel sp = new ShapePanel();
        window.add(sp);
        
        
    }
}
    