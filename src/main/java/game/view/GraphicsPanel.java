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

/**
 * The GraphicsPanel class extends JPanel and is responsible for rendering visual elements
 * onto a buffered image and displaying it. It also provides functionality to save the 
 * rendered image as a PNG file.
 * 
 * <p>This class maintains a list of visual elements to be drawn and supports resetting 
 * the list after each paint operation. The background color of the panel can be set 
 * using the provided method.</p>
 * 
 * <p>Note: The image and visual elements are marked as transient to avoid serialization issues.</p>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #GraphicsPanel()}: Constructor that initializes the list of visual elements.</li>
 *   <li>{@link #reset()}: Resets the list of visual elements.</li>
 *   <li>{@link #drawOnImage()}: Draws the visual elements onto a buffered image and returns it.</li>
 *   <li>{@link #paintComponent(Graphics)}: Overrides the paintComponent method to draw the image onto the panel and reset the visual elements.</li>
 *   <li>{@link #addVisual(Visual)}: Adds a visual element to the list.</li>
 *   <li>{@link #getImagePath()}: Saves the current image as a PNG file and returns the file path.</li>
 *   <li>{@link #setBackgroundColor(Color)}: Sets the background color of the panel.</li>
 * </ul>
 */
public class GraphicsPanel extends JPanel{

    Color backgroundColor;

    transient BufferedImage image;

    /**
     * A list of visual elements to be rendered on the graphics panel.
     * Is reset after each paint operation.
     */
    transient List<Visual> visualElements;

    public GraphicsPanel(){
        visualElements = new ArrayList<>();
    }

    public void reset(){
        visualElements = new ArrayList<>();
    }

    /**
     * Draws the visual elements on a new BufferedImage.
     * 
     * This method creates a new BufferedImage with the current width and height
     * of the panel, sets the background color, and then draws each visual element
     * onto the image using their respective draw methods.
     * 
     * @return A BufferedImage containing the drawn visual elements.
     */
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

    /**
     * Overrides the paintComponent method to perform custom painting.
     * This method is called whenever the component needs to be repainted.
     *
     * @param g the Graphics object used for painting
     */
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

    /**
     * Saves the current image to a file and returns the absolute path of the saved file.
     *
     * @return The absolute path of the saved image file, or null if an exception occurs.
     */
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
