package game.view;

import java.awt.geom.Ellipse2D;

import game.model.Vector2D;
import game.model.physicksbodies.*;
import game.model.Player;
import game.model.Player.Face;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Visualizer{
    public interface Visual{
        void draw(Graphics2D g2);
    }

    public interface Visualizable{
        public abstract Visual getVisual(Visualizer visualizer);
    }

    Rectangle windowBounds;

    public Visualizer(Rectangle windowBounds){
        this.windowBounds = windowBounds;
    }

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

    class WallVisual implements Visual{
        @Override
        public void draw(Graphics2D g2) {
            //invisible
        }            
    }
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
            this.x = x;
            this.y = y;
        }

        @Override
        public void draw(Graphics2D g2) {
            body.draw(g2);
            g2.setColor(Color.BLACK);
            g2.setFont(face.getFont());
            g2.drawString(face.getString(), x + 12 ,y + 40);
        }
    }

    public Visual visualize(Ball ball){
        return new BallVisual(
            ball.getPosition().getX() - windowBounds.x,
            ball.getPosition().getY() - windowBounds.y,
            ball.getDimension().getX(),
            ball.getDimension().getY(),
            ball.getColor(),
            ball.isSelected());
    }

    public Visual visualize(Brick brick){
        return new BrickVisual(
            brick.getPosition().getX() - windowBounds.getX(),
            brick.getPosition().getY() - windowBounds.getY(),
            brick.getDimension(),brick.getColor(),brick.isSelected());
    }

    public Visual visualize(Pole pole){
        return new BallVisual(
            pole.getPosition().getX() - windowBounds.x,
            pole.getPosition().getY() - windowBounds.y,
            pole.getDimension().getX(),
            pole.getDimension().getY(),
            pole.getColor(),
            pole.isSelected());
    }

    public Visual visualize(Player player){
        Ball body = player.getBody();
        return new PlayerVisual(body, player.getFace(), (int)body.getPosition().getX(), (int)body.getPosition().getY());
    }
}
