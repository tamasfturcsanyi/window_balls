package game.view;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

import game.model.Vector2D;

public class FixLabel extends JLabel {
    Vector2D position;

    public FixLabel(String text, Vector2D position){
        super(text);
        this.position = position;

        setBounds((int)position.getX(),(int)position.getY(),100,100);
    }

    public void updatePosition(Rectangle2D windowBounds){
        setBounds((int)(position.getX() - windowBounds.getX()),(int)(position.getY() - windowBounds.getY()),100,100);
    }
    
}
