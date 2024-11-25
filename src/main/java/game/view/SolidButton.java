package game.view;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import game.model.Vector2D;
import game.model.physicsbodies.Brick;

import java.awt.Rectangle;

/**
 * SolidButton is a custom JButton with additional features such as a Brick body,
 * hover effect, and custom styling.
 * It participates in the Simulation while being functional as a JButton.
 */
public class SolidButton extends JButton{
    Color normalColor = Color.WHITE;
    
    transient Brick body;

    /**
     * Constructs a SolidButton with the specified title, position, and dimensions.
     *
     * @param title the title of the button
     * @param position the position of the button as a Vector2D object
     * @param dimensions the dimensions of the button as a Vector2D object
     */
    public SolidButton(String title, Vector2D position, Vector2D dimensions){
        super(title);
        body = new Brick(position, dimensions);
        body.setVisible(false);
    }

    public Brick getBody() {
        return body;
    }

    /**
     * Updates the appearance of the button based on its rollover state.
     * If the button is in a rollover state (i.e., the mouse is hovering over it),
     * the background color is set to a darker shade of the normal color.
     * Otherwise, the background color is set to the normal color.
     */
    public void updateApperence(){
        ButtonModel model = getModel();
        if (model.isRollover()) {
            setBackground(normalColor.darker());  // Hover color
        }
         else {
            setBackground(normalColor);        // Normal color
        }
    }

    /**
     * Updates the position of the button based on the given window bounds, so it appears fixed
     *
     * @param windowBounds the bounds of the window used to adjust the button's position
     */
    public void updatePosition(Rectangle windowBounds){
        this.setBounds( (int)body.getPosition().getX() - windowBounds.x,
                        (int)body.getPosition().getY() - windowBounds.y,
                        (int)body.getDimension().getX(),
                        (int)body.getDimension().getY());
    }

    /**
     * Applies the "Impact" style to the button.
     * This includes setting the font to "Impact" with bold style and size 32,
     * painting the border with a black line of thickness 5,
     * and disabling the focus painting.
     */
    public void impactStyle(){
        setFont(new Font("Impact",Font.BOLD,32));
        setBorderPainted(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        setFocusPainted(false);
    }

    public void setNormalColor(Color normalColor) {
        this.normalColor = normalColor;
    }
}
