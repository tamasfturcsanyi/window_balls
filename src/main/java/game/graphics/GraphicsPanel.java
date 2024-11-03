package game.graphics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel{

    transient List<Visual> visualElements;

    public GraphicsPanel(){
        visualElements = new ArrayList<>();
    }

    public void updateElements(Rectangle windowBounds){
        for (Visual visual : visualElements) {
            visual.updateVisuals(windowBounds);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D )g;

        for (Visual v : visualElements) {
            g2.setColor(v.getColor());
            g2.fill(v.getShape());
            //g2.draw(v.getShape().getBounds2D());
        }
    }

    public void addVisual(Visual v){
        visualElements.add(v);
    }
}
