package game.view;

import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

import game.model.Vector2D;

/**
 * The FixLabel class extends JLabel and provides additional functionality to update its position
 * based on a given window's bounds. It uses a Vector2D object to store its position.
 * It apears fix while the window is resized or moved.
 * 
 * <p>This class includes methods to set the position and update the position relative to a window's bounds.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * JFrame window = new JFrame();
 * window.setBounds(100, 100, 300, 200);
 * 
 * JPanel panel = new JPanel();
 * panel.setLayout(new FlowLayout());
 * panel.setBackground(Color.blue);
 * 
 * FixLabel label = new FixLabel("Hello", new Vector2D(0, 0));
 * label.setFont(new Font("Impact", Font.BOLD, 100));
 * label.setForeground(Color.ORANGE);
 * panel.add(label);
 * window.add(panel);
 * window.setVisible(true);
 * 
 * while (true) {
 *     label.updatePosition(window.getBounds());
 * }
 * }
 * </pre>
 * 
 * @see JLabel
 * @see Vector2D
 * @see Rectangle2D
 */
public class FixLabel extends JLabel {
    transient Vector2D position = new Vector2D();

    public FixLabel(String text, Vector2D position){
        super(text);
        this.position = position;
    }

    public void setPosition(Vector2D position){
        this.position = position;
    }


    /**
     * Updates the position of the label based on the given window bounds.
     *
     * @param windowBounds the bounds of the window used to adjust the label's position
     */
    public void updatePosition(Rectangle2D windowBounds){
        setBounds((int)(position.getX() - windowBounds.getX()),(int)(position.getY() - windowBounds.getY()),getWidth(),getHeight());
    }
}
