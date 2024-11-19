package game.view;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

import game.model.Vector2D;

public class FixLabel extends JLabel {
    transient Vector2D position = new Vector2D();

    public FixLabel(String text, Vector2D position){
        super(text);
        this.position = position;
    }

    public void updatePosition(Rectangle2D windowBounds){
        setBounds((int)(position.getX() - windowBounds.getX()),(int)(position.getY() - windowBounds.getY()),getWidth(),getHeight());
    }
    
}
