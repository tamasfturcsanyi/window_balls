package ttm;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel{

    List<Shape> shapes;

    GraphicsPanel(){
        shapes = new ArrayList<>();
    }


    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D )g;

        for (Shape shape : shapes) {
            g2.fill(shape);
            //System.out.println(shape.toString() + "drawn");
        }
    }

    void addShape(Shape s){
        shapes.add(s);
    }
}
