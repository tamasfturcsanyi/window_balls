package game.view;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;

import game.model.Vector2D;

public class FixLabel extends JLabel {
    transient Vector2D position = new Vector2D();

    public FixLabel(String text, Vector2D position){
        super(text);
        this.position = position;
    }

    public void setPosition(Vector2D position){
        this.position = position;
    }


    public void updatePosition(Rectangle2D windowBounds){
        setBounds((int)(position.getX() - windowBounds.getX()),(int)(position.getY() - windowBounds.getY()),getWidth(),getHeight());
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setBounds(100, 100, 300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.blue);

        FixLabel label = new FixLabel("Hello",new Vector2D(0, 0));
        label.setFont(new Font("Impact",Font.BOLD,100));
        label.setForeground(Color.ORANGE);
        panel.add(label);
        window.add(panel);
        window.setVisible(true);

        while (true) {
            label.updatePosition(window.getBounds());
        }
    }
    
}
