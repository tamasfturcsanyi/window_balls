package shapeTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import game.model.Vector2D;
import game.model.shapes.CollisionRectangle;
import java.awt.geom.Rectangle2D;

import physicsTests.Vector2DTest;

class CollisionRectangleTest {

    static double limes = 0.0000001;

    private CollisionRectangle rectangle;
    private Vector2D position;
    private Vector2D dimension;

    @BeforeEach
    public void setUp() {
        position = new Vector2D(0, 0);
        dimension = new Vector2D(10, 5);
        rectangle = new CollisionRectangle(position, dimension);
    }

    @Test
    void testGetPosition() {
        assertEquals(position, rectangle.getPosition());
    }

    @Test
    void testSetPosition() {
        Vector2D newPosition = new Vector2D(5, 5);
        rectangle.setPosition(newPosition);
        assertEquals(newPosition, rectangle.getPosition());
        assertEquals(newPosition.getX(), rectangle.getX());
        assertEquals(newPosition.getY(), rectangle.getY());
    }

    @Test
    void testGetBoundingBox() {
        Rectangle2D boundingBox = rectangle.getBoundingBox();
        assertEquals(rectangle, boundingBox);
    }

    @Test
    void testGetCenter() {
        Vector2D center = rectangle.getCenter();
        Vector2DTest.assertVectorEquals(new Vector2D(5, 2.5), center);
    }

    @Test
    void testGetMaxDistanceFromCenter() {
        double maxDistance = rectangle.getMaxDistanceFromCenter();
        assertEquals(5.590169943749474, maxDistance,limes);
    }

    @Test
    void testHaveCollided() {
        CollisionRectangle otherRectangle = new CollisionRectangle(new Vector2D(5, 0), new Vector2D(10, 5));
        assertTrue(rectangle.haveCollided(otherRectangle));

        CollisionRectangle nonCollidingRectangle = new CollisionRectangle(new Vector2D(20, 20), new Vector2D(10, 5));
        assertFalse(rectangle.haveCollided(nonCollidingRectangle));
    }

    @Test
    void testEquals() {
        CollisionRectangle sameRectangle = new CollisionRectangle(position, dimension);
        assertTrue(rectangle.equals(sameRectangle));//NOSONAR

        CollisionRectangle differentRectangle = new CollisionRectangle(new Vector2D(1, 1), new Vector2D(10, 5));
        assertFalse(rectangle.equals(differentRectangle));//NOSONAR
    }

    @Test
    void testHashCode() {
        CollisionRectangle sameRectangle = new CollisionRectangle(position, dimension);
        assertEquals(rectangle.hashCode(), sameRectangle.hashCode());
    }
}

