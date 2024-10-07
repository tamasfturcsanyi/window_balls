package ttm;

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

    public Vector2D(double angle, double lenght,boolean DEG){
        if(DEG){
            angle = Math.toRadians(angle);
        }
        
        x = Math.cos(angle)*lenght;
        y =  -1 * Math.sin(angle)*lenght;
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

    public Vector2D rotate(double radAngle){
        return new Vector2D(Math.atan2(y,x) + radAngle,length(),false);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Vector2D vect = (Vector2D) obj;
        return (this.x == vect.getX() && this.y == vect.getY());
    }

    @Override
    public String toString(){
        return "x: " + getX() + " y: " + getY();
    }
}
