import org.junit.jupiter.api.Test;

import game.engine.Direction;
import game.engine.Wall;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;


class WallTest{
    @Test
    void testConstructorNorth(){
        Rectangle windowBounds = new Rectangle(0, 0, 500, 300);

        Wall wall = new Wall(Direction.NORTH, 200);
        wall.updateVisuals(windowBounds);

        assertEquals(0 - wall.getBonus(),wall.getBody().getPosition().getX());
        assertEquals(-200,wall.getBody().getPosition().getY());
        assertEquals(500 + wall.getBonus(), wall.getBody().getShape().getBounds2D().getWidth());
        assertEquals(200, wall.getBody().getShape().getBounds2D().getHeight());
        
    }

    @Test
    void testConstructorEast(){
        Rectangle windowBounds = new Rectangle(0, 0, 500, 300);

        Wall wall = new Wall(Direction.EAST, 200);
        wall.updateVisuals(windowBounds);

        assertEquals(500 - wall.getOffsetEast(),wall.getBody().getPosition().getX());
        assertEquals(0 - wall.getBonus(),wall.getBody().getPosition().getY());
        
        assertEquals(200, wall.getBody().getShape().getBounds2D().getWidth());
        assertEquals(300 + wall.getBonus(), wall.getBody().getShape().getBounds2D().getHeight());
    }

    @Test
    void testConstructorWest(){
        Rectangle windowBounds = new Rectangle(0, 0, 500, 300);

        Wall wall = new Wall(Direction.WEST, 200);
        wall.updateVisuals(windowBounds);

        assertEquals(-200,wall.getBody().getPosition().getX());
        assertEquals(0 - wall.getBonus(),wall.getBody().getPosition().getY());
        
        assertEquals(200, wall.getBody().getShape().getBounds2D().getWidth());
        assertEquals(300 + wall.getBonus(), wall.getBody().getShape().getBounds2D().getHeight());
    }
}