import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ttm.Vector2D;

public class Vector2DTest {

    @Test
    void testConstructor(){
        Vector2D p1 = new Vector2D();
        Vector2D p2 = new Vector2D(3, 4);

        assertEquals(0, p1.getX());
        assertEquals(0 ,p1.getY());
        assertEquals(3, p2.getX());
        assertEquals(4, p2.getY());

    }

    @Test
    void testAdd(){
        Vector2D nulla = new Vector2D();
        Vector2D vect1 = new Vector2D(1,2);
        Vector2D vect2 = new Vector2D(3, 4);

        Vector2D result = vect1.add(vect2);

        assertEquals(new Vector2D(1,2), vect1);
        assertEquals(new Vector2D(3,4), vect2);
        assertEquals(new Vector2D(4, 6), result);
    }

    @Test
    void testDistance(){
        Vector2D p1 = new Vector2D(0, 0);
        Vector2D p2 = new Vector2D(3, 4);

        double distance = p1.distance(p2);
        assertEquals(5,distance);
    }

    


    
}
