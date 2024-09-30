package ttm;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel{

    List<Visual> visualElements;

    GraphicsPanel(){
        visualElements = new ArrayList<>();
    }

    void updateElements(Rectangle windowBounds){
        for (Visual visual : visualElements) {
            visual.updateVisuals(windowBounds);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D )g;

        for (Visual v : visualElements) {
            g2.fill(v.getShape());
            //System.out.println(shape.toString() + "drawn");
        }
    }

    void addVisual(Visual v){
        visualElements.add(v);
    }
}
