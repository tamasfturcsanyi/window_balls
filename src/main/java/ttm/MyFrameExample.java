package ttm;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrameExample {
    public static void main(String[] args) {
        // Create a new JFrame with a title
        JFrame frame = new JFrame("My First JFrame");
        
        // Set the size of the frame (width, height)
        frame.setSize(400, 300);
        
        // Create a button and add it to the frame
        JButton button = new JButton("Click Me");

        Rectangle rectangle = new Rectangle(100,100,100,100);

        frame.add(button);

        BasicStroke stroke = new BasicStroke();
        stroke.createStrokedShape(rectangle);
        
        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
