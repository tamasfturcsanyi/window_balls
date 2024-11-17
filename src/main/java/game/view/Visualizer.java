package game.view;

import java.awt.geom.Ellipse2D;

import game.model.Vector2D;
import game.model.physicksbodies.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Visualizer{
    Rectangle windowBounds;

    public Visualizer(Rectangle windowBounds){
        this.windowBounds = windowBounds;
    }

    class BrickVisual implements Visual{
        Rectangle shape;
        Color color;

        BrickVisual(double x, double y, Vector2D dimension, Color color){
            this.shape = new Rectangle(
                (int)x,
                (int)y,
                (int)dimension.getX(),
                (int)dimension.getY());
            this.color = color;
        }

        @Override
        public void draw(Graphics2D g2) {
            g2.setColor(color);
            g2.fill(shape);
        }
    }

    class WallVisual implements Visual{
        @Override
        public void draw(Graphics2D g2) {
            //invisible
        }            
    }
    class BallVisual implements Visual{
        Ellipse2D.Double shape;
        Color color;

        BallVisual(double x, double y,double width, double height, Color color){
            this.color = color;
            shape = new Ellipse2D.Double(x,y,width,height);
        }

       @Override
       public void draw(Graphics2D g2) {
           g2.setColor(color);
           g2.fill(shape);
       }
    }

    public Visual visualize(Ball ball){
        return new BallVisual(
            ball.getPosition().getX() - windowBounds.x,
            ball.getPosition().getY() - windowBounds.y,
            ball.getDimension().getX(),
            ball.getDimension().getY(),
            ball.getColor());
    }

    public Visual visualize(Brick brick){
        return new BrickVisual(
            brick.getPosition().getX() - windowBounds.getX(),
            brick.getPosition().getY() - windowBounds.getY(),
            brick.getDimension(),brick.getColor());
    }

    public Visual visualize(Pole pole){
        return new BallVisual(
            pole.getPosition().getX() - windowBounds.x,
            pole.getPosition().getY() - windowBounds.y,
            pole.getDimension().getX(),
            pole.getDimension().getY(),
            pole.getColor());
    }
}
