package physicsTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import game.model.Vector2D;
import game.model.physicsbodies.Wall;
import game.model.shapes.CollisionRectangle;

class WallTest {
    static double limes = 0.0000001;

    private Wall wallNorth;
    private Wall wallEast;
    private Wall wallSouth;
    private Wall wallWest;
    private Rectangle windowBounds;

    @BeforeEach
    public void setUp() {
        windowBounds = new Rectangle(0, 0, 800, 600);
        wallNorth = new Wall(Wall.Direction.NORTH, 50, windowBounds);
        wallEast = new Wall(Wall.Direction.EAST, 50, windowBounds);
        wallSouth = new Wall(Wall.Direction.SOUTH, 50, windowBounds);
        wallWest = new Wall(Wall.Direction.WEST, 50, windowBounds);
    }

    @Test
    void testWallInitialization() {
        assertNotNull(wallNorth);
        assertNotNull(wallEast);
        assertNotNull(wallSouth);
        assertNotNull(wallWest);
    }

    @Test
    void testWallUpdate() {
        wallNorth.update(windowBounds);
        wallEast.update(windowBounds);
        wallSouth.update(windowBounds);
        wallWest.update(windowBounds);

        Vector2DTest.assertVectorEquals(new Vector2D(-50, -50), ((CollisionRectangle) wallNorth.getCollisionShape()).getPosition());
        Vector2DTest.assertVectorEquals(new Vector2D(785, -50), ((CollisionRectangle) wallEast.getCollisionShape()).getPosition());
        Vector2DTest.assertVectorEquals(new Vector2D(-50, 560), ((CollisionRectangle) wallSouth.getCollisionShape()).getPosition());
        Vector2DTest.assertVectorEquals(new Vector2D(-50, -50), ((CollisionRectangle) wallWest.getCollisionShape()).getPosition());
    }

    @Test
    void testWallBounce() {
        CollisionRectangle testShape = new CollisionRectangle(new Vector2D(100, 100), new Vector2D(50, 50));

        Vector2D bounceNorth = wallNorth.bounce(testShape);
        Vector2D bounceEast = wallEast.bounce(testShape);
        Vector2D bounceSouth = wallSouth.bounce(testShape);
        Vector2D bounceWest = wallWest.bounce(testShape);

        assertEquals(0, bounceNorth.getX(), limes);
        assertEquals(0, bounceEast.getY(), limes);
        assertEquals(0, bounceSouth.getX(), limes);
        assertEquals(0, bounceWest.getY(), limes);
        assertTrue(0 < bounceNorth.getY());
        assertTrue(0 > bounceEast.getX());
        assertTrue(0 > bounceSouth.getY());
        assertTrue(0 < bounceWest.getX());
    }

    @Test
    void testWallPosition() {
        Vector2D newPosition = new Vector2D(200, 200);
        wallNorth.setPosition(newPosition);
        Vector2DTest.assertVectorEquals(newPosition, wallNorth.getPosition());
    }

    @Test
    void testWallDimension() {
        wallNorth.update(windowBounds);
        Vector2D dimension = wallNorth.getDimension();
        Vector2DTest.assertVectorEquals(new Vector2D(850, 50), dimension);
    }
}
