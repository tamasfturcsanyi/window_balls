import org.junit.jupiter.api.Test;

import game.model.Vector2D;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;

class Vector2DTest {

    double limes = 0.0000001;

    @Test
    void testConstructorXY(){
        Vector2D p1 = new Vector2D();
        Vector2D p2 = new Vector2D(3, 4);

        assertEquals(0, p1.getX());
        assertEquals(0 ,p1.getY());
        assertEquals(3, p2.getX());
        assertEquals(4, p2.getY());

    }

    @Test
    void testConstructorDEG(){
        Vector2D vect1 = new Vector2D(0, 1, true);

        assertEquals(1,vect1.getX());
        assertEquals(0, vect1.getY(), limes);
        

        Vector2D vect2 = new Vector2D(90, 1, true);

        assertEquals(0, vect2.getX(), limes);
        assertEquals(-1, vect2.getY());

        Vector2D vect3 = new Vector2D(45,Math.sqrt(2),true);

        assertEquals(1,vect3.getX(), limes);
        assertEquals(-1,vect3.getY(), limes);

        Vector2D vect4 = new Vector2D(180,1,true);

        assertEquals(-1,vect4.getX(), limes);
        assertEquals(0,vect4.getY(), limes);

        Vector2D vect5 = new Vector2D(270,1,true);

        assertEquals(0,vect5.getX(), limes);
        assertEquals(1,vect5.getY(), limes);
    }

    @Disabled
    @Test
    void testAdd(){
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

    @Test
    void testRotate(){
        Vector2D vect1 = new Vector2D(0, 1, true);
        

        assertEquals(1, vect1.getX(), limes);
        assertEquals(0, vect1.getY(), limes);

        vect1 = vect1.rotate(Math.toRadians(90));

        assertEquals(0, vect1.getX(), limes);
        assertEquals(-1, vect1.getY(), limes);

    }

    @Disabled
    @Test
    void testDiff(){
        Vector2D vect1 = new Vector2D(10,5);
        Vector2D vect2 = new Vector2D(12,7);


        assertEquals(new Vector2D(2,2),vect1.diff( vect2));
        assertEquals(new Vector2D(-2,-2),vect2.diff( vect1));
    }


    
}
