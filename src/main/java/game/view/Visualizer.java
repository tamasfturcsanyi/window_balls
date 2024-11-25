package game.view;

import java.awt.geom.Ellipse2D;

import game.model.Vector2D;
import game.model.Player;
import game.model.Player.Face;
import game.model.physicsbodies.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * The Visualizer class provides a way to visualize the game objects in the simulation.
 * It follows the Model-View-Controller design pattern and provides methods to visualize
 * Follows visitor pattern to visualize different objects in the game.
 */
public class Visualizer{
    /**
     * The Visual interface represents a drawable object that can be rendered using a Graphics2D context.
     */
    public interface Visual{
        void draw(Graphics2D g2);
    }

    /**
     * The Visualizable interface should be implemented by any class whose instances 
     * are intended to be visualized. The implementing class must provide an 
     * implementation for the getVisual method, which returns a Visual object 
     * representing the visual representation of the implementing class.
     */
    public interface Visualizable{
        public abstract Visual getVisual(Visualizer visualizer);
    }

    Rectangle windowBounds;

    public Visualizer(Rectangle windowBounds){
        this.windowBounds = windowBounds;
    }

    /**
     * The BrickVisual class implements the Visual interface and represents a visual
     * representation of a brick in the game. It contains a shape, color, and a 
     * selected state to indicate if the brick is selected.
     */
    class BrickVisual implements Visual{
        Rectangle shape;
        Color color;
        boolean selected = false;

        BrickVisual(double x, double y, Vector2D dimension, Color color, boolean selected){
            this.shape = new Rectangle(
                (int)x,
                (int)y,
                (int)dimension.getX(),
                (int)dimension.getY());
            this.color = color;
            this.selected = selected;
        }

        @Override
        public void draw(Graphics2D g2) {
            g2.setColor(color);
            g2.fill(shape);

            if(selected){
                g2.setStroke(new java.awt.BasicStroke(5));
                g2.setColor(Color.YELLOW);
                g2.draw(shape);
            }
        }
    }

    /**
     * WallVisual is an implementation of the Visual interface that represents a wall in the game.
     * The draw method is overridden to provide no visual representation, making the wall invisible.
     */
    class WallVisual implements Visual{
        @Override
        public void draw(Graphics2D g2) {
            //invisible
        }            
    }
    /**
     * The BallVisual class represents a visual representation of a ball using an Ellipse2D shape.
     * It implements the Visual interface and provides a method to draw the ball on a Graphics2D context.
     */
    class BallVisual implements Visual{
        Ellipse2D.Double shape;
        Color color;
        boolean selected = false;

        BallVisual(double x, double y,double width, double height, Color color, boolean selected){
            this.color = color;
            shape = new Ellipse2D.Double(x,y,width,height);
            this.selected = selected;
        }

       @Override
       public void draw(Graphics2D g2) {
           g2.setColor(color);
           g2.fill(shape);
           if(selected){
               g2.setColor(Color.YELLOW);
               g2.draw(shape);
           }
       }
    }

    /**
     * The PlayerVisual class implements the Visual interface and represents the visual 
     * representation of a player in the game. It consists of a BallVisual object for the 
     * player's body and a Face object for the player's face. The position of the player 
     * is determined by the x and y coordinates.
     */
    class PlayerVisual implements Visual{
        BallVisual body;
        Face face;
        int x;
        int y;

        PlayerVisual(Ball ball, Face face,int x, int y){
            this.body = new BallVisual(
                ball.getPosition().getX() - windowBounds.x,
                ball.getPosition().getY() - windowBounds.y,
                ball.getDimension().getX(),
                ball.getDimension().getY(),
                ball.getColor(),
                false);
            this.face = face;
            this.x = x + face.getOffsetX();
            this.y = y + face.getOffsetY();
        }

        @Override
        public void draw(Graphics2D g2) {
            body.draw(g2);
            g2.setColor(Color.BLACK);
            g2.setFont(face.getFont());
            g2.drawString(face.getString(), x ,y);
        }
    }

    /**
     * Creates a visual representation of the given ball.
     *
     * @param ball the ball to be visualized
     * @return a BallVisual object representing the visual attributes of the ball
     */
    public Visual visualize(Ball ball){
        return new BallVisual(
            ball.getPosition().getX() - windowBounds.x,
            ball.getPosition().getY() - windowBounds.y,
            ball.getDimension().getX(),
            ball.getDimension().getY(),
            ball.getColor(),
            ball.isSelected());
    }

    /**
     * Creates a visual representation of a given Brick object.
     *
     * @param brick the Brick object to be visualized
     * @return a BrickVisual object representing the visual representation of the given Brick
     */
    public Visual visualize(Brick brick){
        return new BrickVisual(
            brick.getPosition().getX() - windowBounds.getX(),
            brick.getPosition().getY() - windowBounds.getY(),
            brick.getDimension(),brick.getColor(),brick.isSelected());
    }

    /**
     * Creates a visual representation of a Pole object.
     *
     * @param pole the Pole object to be visualized
     * @return a BallVisual object representing the visualized Pole
     */
    public Visual visualize(Pole pole){
        return new BallVisual(
            pole.getPosition().getX() - windowBounds.x,
            pole.getPosition().getY() - windowBounds.y,
            pole.getDimension().getX(),
            pole.getDimension().getY(),
            pole.getColor(),
            pole.isSelected());
    }

    /**
     * Creates a visual representation of the given player.
     *
     * @param player the player to visualize
     * @return a PlayerVisual object representing the player's visual state
     */
    public Visual visualize(Player player){
        Ball body = player.getBody();
        return new PlayerVisual(body, player.getFace(), (int)body.getPosition().getX(), (int)body.getPosition().getY());
    }
}
