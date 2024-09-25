package ttm;
public class Point {
    public double x = 0;
    public double y = 0;

    public Point(double xkoord, double ykoord){
        x = xkoord;
        y = ykoord;
    }

    //returns the distance between this and p, with pithagorasz    
    public double distance(Point p){

        //calculating the diff
        double dx = Math.abs(p.x - this.x)  ;
        double dy = Math.abs(p.y - this.y );

        //ptihagorasz
        return Math.sqrt(dy*dy + dx*dx);
    }
}
