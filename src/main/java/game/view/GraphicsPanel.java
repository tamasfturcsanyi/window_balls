package game.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import game.view.Visualizer.Visual;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel{

    Color backgroundColor;

    transient BufferedImage image;

    transient List<Visual> visualElements;

    public GraphicsPanel(){
        visualElements = new ArrayList<>();
    }

    public void reset(){
        visualElements = new ArrayList<>();
    }

    public BufferedImage drawOnImage(){
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D imageGraphics2d = image.createGraphics();
        imageGraphics2d.setColor(backgroundColor);
        imageGraphics2d.fillRect(0,0, getWidth(), getHeight());
        for (Visual v : visualElements) {
            v.draw(imageGraphics2d);
        }
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D )g;

        g2.drawImage(drawOnImage(),0,0,this);

        reset();
    }

    public void addVisual(Visual v){
        visualElements.add(v);
    }

    //creates a png about the simulation and returns its path
    public String getImagePath(){
        try {
            File outputfile = new File("src/main/resources/simulation.png");
            javax.imageio.ImageIO.write(image, "png", outputfile);
            return outputfile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
