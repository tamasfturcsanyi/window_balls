package shapeTests;

import game.model.Vector2D;
import game.model.shapes.CollisionCircle;
import org.junit.jupiter.api.Test;
import java.awt.geom.Rectangle2D;
import static org.junit.jupiter.api.Assertions.*;

import physicsTests.Vector2DTest;





class CollisionCircleTest {

    @Test
    void testGetPosition() {
        Vector2D position = new Vector2D(10, 20);
        CollisionCircle circle = new CollisionCircle(position, 5);
        assertEquals(position, circle.getPosition());
    }

    @Test
    void testSetPosition() {
        Vector2D position = new Vector2D(10, 20);
        CollisionCircle circle = new CollisionCircle(position, 5);
        Vector2D newPosition = new Vector2D(30, 40);
        circle.setPosition(newPosition);
        assertEquals(newPosition, circle.getPosition());
    }

    @Test
    void testHaveCollided() {
        Vector2D position1 = new Vector2D(10, 20);
        CollisionCircle circle1 = new CollisionCircle(position1, 5);
        Vector2D position2 = new Vector2D(15, 25);
        CollisionCircle circle2 = new CollisionCircle(position2, 5);
        assertTrue(circle1.haveCollided(circle2));
    }

    @Test
    void testHaveNotCollided() {
        Vector2D position1 = new Vector2D(10, 20);
        CollisionCircle circle1 = new CollisionCircle(position1, 5);
        Vector2D position2 = new Vector2D(50, 50);
        CollisionCircle circle2 = new CollisionCircle(position2, 5);
        assertFalse(circle1.haveCollided(circle2));
    }

    @Test
    void testGetBoundingBox() {
        Vector2D position = new Vector2D(10, 20);
        CollisionCircle circle = new CollisionCircle(position, 5);
        Rectangle2D boundingBox = circle.getBoundingBox();
        assertEquals(10, boundingBox.getX());
        assertEquals(20, boundingBox.getY());
        assertEquals(10, boundingBox.getWidth());
        assertEquals(10, boundingBox.getHeight());
    }

    @Test
    void testGetMaxDistanceFromCenter() {
        Vector2D position = new Vector2D(10, 20);
        CollisionCircle circle = new CollisionCircle(position, 5);
        assertEquals(5, circle.getMaxDistanceFromCenter());
    }

    @Test
    void testGetCenter() {
        Vector2D position = new Vector2D(10, 20);
        CollisionCircle circle = new CollisionCircle(position, 5);
        Vector2D center = circle.getCenter();
        Vector2DTest.assertVectorEquals(new Vector2D(15, 25), center);
    }
}

