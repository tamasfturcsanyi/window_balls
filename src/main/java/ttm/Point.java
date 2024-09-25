package ttm;
public class Point {
    double x = 0;
    double y = 0;

    Point(double xkoord, double ykoord){
        x = xkoord;
        y = ykoord;
    }

    //returns the distance between this and p, with pithagorasz    
    double distance(Point p){

        //calculating the diff
        double dx = Math.abs(p.x - this.x)  ;
        double dy = Math.abs(p.y - this.y );

        //ptihagorasz
        return Math.sqrt(dy*dy + dx*dx);
    }
}
