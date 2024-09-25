package ttm;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

public class Ball {
    double bounciness = 1;
    
    Ellipse2D.Double body;

    Ball(){
        body = new Ellipse2D.Double(0,0,100,100);
    }
}
