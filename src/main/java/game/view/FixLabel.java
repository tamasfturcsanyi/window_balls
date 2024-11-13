package game.view;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JLabel;

import game.model.Vector2D;

public class FixLabel extends JLabel {
    transient Vector2D position = new Vector2D();

    public FixLabel(String text){
        super(text);
        this.setFont(new Font("Impact",Font.BOLD,50));
        setBackground(Color.RED);
        setForeground(Color.ORANGE);
    }
    public FixLabel(String text, Vector2D position){
        super(text);
        this.position = position;
        this.setFont(new Font("Impact",Font.BOLD,50));
        setBackground(Color.RED);
        setForeground(Color.ORANGE);
    }

    public void updatePosition(Rectangle2D windowBounds){
        setBounds((int)(position.getX() - windowBounds.getX()),(int)(position.getY() - windowBounds.getY()),1000,100);
    }
    
}
