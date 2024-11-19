package game.view;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import game.view.Visualizer.Visual;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel{

    transient BufferedImage image;

    transient List<Visual> visualElements;

    public GraphicsPanel(){
        visualElements = new ArrayList<>();
    }

    public void reset(){
        visualElements = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = (Graphics2D )g;
        Graphics2D imageGraphics2d = image.createGraphics();

        for (Visual v : visualElements) {
            v.draw(imageGraphics2d);
        }

        g2.drawImage(image,0,0,this);

        reset();
    }

    public void addVisual(Visual v){
        visualElements.add(v);
    }
}
