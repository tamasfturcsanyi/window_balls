package game.engine;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
public class Brick extends Actor{
    Rectangle body;

    double bounciness = 0.1;

    public Brick(Vector2D position, Vector2D dimension){
        super(1, true);
        body = new Rectangle(position, dimension,Color.ORANGE);
    }

    @Override
    public void updateVisuals(java.awt.Rectangle windowBounds) {
        body.updateVisuals(windowBounds);        
    }

    @Override
    public Vector2D getCenter() {
        return body.getPosition().add(body.dimension.stretch(0.5));
    }

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }

    @Override
    Vector2D getPos() {
        return body.position;
    }

    @Override
    void setPos(Vector2D newPos) {
        body.position = newPos;
    }

    @Override
    public Shape getShape() {
        return body.getShape();
    }

    @Override
    public Vector2D bounce(Actor a) {
        Rectangle2D rect =  getShape().getBounds2D().createIntersection(a.getShape().getBounds2D());
        if (body.position.getY() - a.getPos().getY() < 0) {
            return new Vector2D(0,rect.getHeight()).stretch(rect.getHeight()*bounciness);
        }else if(body.position.getY() - a.getPos().getY() > 0){
            return new Vector2D(0,-rect.getHeight()).stretch(rect.getHeight()*bounciness);
        }

        return new Vector2D(0,0);
    }

    @Override
    void calculateForces(SimulationParameters params) {
        //Brick does not move
    }

    @Override
    void calculateNewPosition(double delta) {
        //Brick does not move        
    }

    @Override
    void calculateNewVelocity(double delta, SimulationParameters params) {
         //Brick does not move        
    }

    @Override
    void physicksUpdate(SimulationParameters params) {
        //Brick does not move
    }
}
