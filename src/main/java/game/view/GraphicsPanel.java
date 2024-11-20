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
        //setLayout(null);
    }

    public void reset(){
        visualElements = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Visual v : visualElements) {
            v.draw(g2);
        }
        reset();
    }

    public void addVisual(Visual v){
        visualElements.add(v);
    }
}
