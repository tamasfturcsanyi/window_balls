import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ttm.Point;

public class PointTest {

    @Test
    void testConstructor(){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);

        assertEquals(0, p1.x);
        assertEquals(0 ,p1.y);
        assertEquals(3, p2.x);
        assertEquals(4, p2.y);

    }

    @Test
    void testDistance(){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);

        double distance = p1.distance(p2);
        assertEquals(5,distance);
    }

    


    
}
