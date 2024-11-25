package game.model;

/**
 * The Vector2D class represents a 2-dimensional vector with x and y coordinates.
 * It provides methods for vector addition, subtraction, scaling, and calculating
 * the length and distance between vectors.
 */
public class Vector2D {
    double x;
    double y;

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public Vector2D add(Vector2D vect){
        return new Vector2D(this.x + vect.getX(),this.y + vect.getY());
    }

    public Vector2D diff(Vector2D vect){
        return new Vector2D( vect.getX()- this.x, vect.getY()- this.y);
    }

    public double length(){
        return Math.sqrt((x*x)+(y*y));
    }

    public double distance(Vector2D vect){
        return this.diff(vect).length();
    }

    public Vector2D stretch(double ammount){
        return new Vector2D(x*ammount,y* ammount);
    }

    @Override
    public String toString(){
        return "x: " + getX() + " y: " + getY();
    }
}
