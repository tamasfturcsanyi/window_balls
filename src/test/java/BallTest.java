import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ttm.Ball;
import ttm.PhysicksWorld;
import ttm.Vector2D;


public class BallTest {

    @Test
    void testConstructor(){
        Ball ball1 = new Ball(100,300,20);

        assertEquals(100, ball1.getPos().getX());
        assertEquals(300, ball1.getPos().getY());
        assertEquals(1, ball1.getMass());
    }

    @Test
    void testExternalForce(){
        Ball ball = new Ball(100, 100, 10);
        PhysicksWorld world = new PhysicksWorld();
        world.addBody(ball);
        Vector2D starter = new Vector2D(0,-100);
        ball.addForce(starter);

        assertEquals(starter.getX(), ball.getExternalForce().getX());
        assertEquals(starter.getY(), ball.getExternalForce().getY());
        
        //after first update, no change
        world.update();
        assertEquals(0, ball.getVelocity().getX());
        assertEquals(0, ball.getVelocity().getY());

        world.update();
        assertEquals(0, ball.getExternalForce().getX());
        assertEquals(0, ball.getExternalForce().getY());

        assertEquals(0, ball.getVelocity().getX());
        assertNotEquals(0, ball.getVelocity().getY());

    }

}
