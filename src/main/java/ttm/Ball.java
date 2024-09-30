package ttm;

import ttm.Point;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

public class Ball {
    


    //pos is modified by velocity every frame
    Point velocity;

    //velocity is modified by force/weight every frame
    Point force;

    double mass = 1;
    double bounciness = 1; 


    Ball(){
        //visual = new Ellipse2D.Double(0,0,100,100);
    }
}
