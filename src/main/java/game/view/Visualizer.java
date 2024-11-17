package game.view;

import java.awt.geom.Ellipse2D;

import game.model.Vector2D;
import game.model.physicksbodies.*;
import game.model.physicksbodies.volley.PlayerBody;
import game.model.Player;

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

    class PlayerVisual implements Visual{
        BallVisual body;
        String face;
        int x;
        int y;

        PlayerVisual(Ball ball, String face,int x, int y){
            this.body = new BallVisual(
                ball.getPosition().getX() - windowBounds.x,
                ball.getPosition().getY() - windowBounds.y,
                ball.getDimension().getX(),
                ball.getDimension().getY(),
                ball.getColor());
            this.face = face;
            this.x = x;
            this.y = y;
        }

        @Override
        public void draw(Graphics2D g2) {
            body.draw(g2);
            g2.drawString(face, x,y);
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

    public Visual visualize(Player player){
        PlayerBody body = player.getBody();
        return new PlayerVisual(body, body.getFace(), (int)body.getPosition().getX(), (int)body.getPosition().getY());
    }
}
