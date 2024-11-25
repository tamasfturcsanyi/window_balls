package physicsTests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.model.Vector2D;
import game.model.physicsbodies.Ball;
import game.model.SimulationParameters;

class BallTest {

    private Ball ball;
    private SimulationParameters params;

    @BeforeEach
    public void setUp() {
        ball = new Ball(new Vector2D(0, 0), 10, Color.RED, 0.8, 1.0);
        params = new SimulationParameters(new Vector2D(0, -9.8),1,0.9,50,0.1,false,Color.WHITE);
    }

    @Test
    void testInitialPosition() {
        Vector2DTest.assertVectorEquals(new Vector2D(0, 0), ball.getCenter());
    }

    @Test
    void testSetPosition() {
        ball.setPosition(new Vector2D(5, 5));
        Vector2DTest.assertVectorEquals(new Vector2D(5, 5), ball.getPosition());
    }

    @Test
    void testCalculateForces() {
        ball.calculateForces(params);
        Vector2DTest.assertVectorEquals(new Vector2D(0, -9.8), ball.getForce());
    }

    @Test
    void testCalculateNewVelocity() {
        ball.calculateForces(params);
        ball.calculateNewVelocity(1, params);
        assertTrue(ball.getVelocity().length() > 0);
    }

    @Test
    void testCalculateNewPosition() {
        ball.calculateForces(params);
        ball.calculateNewVelocity(1, params);
        ball.calculateNewPosition(1);
        assertTrue(ball.getPosition().length() > 0);
    }

    @Test
    void testCollide() {
        Ball otherBall = new Ball(new Vector2D(5, 5), 10, Color.BLUE, 0.8, 1.0);
        ball.collide(otherBall);
        assertTrue(ball.getExternalForce().length() > 0);
    }

    @Test
    void testBounce() {
        Ball otherBall = new Ball(new Vector2D(5, 5), 10, Color.BLUE, 0.8, 1.0);
        Vector2D bounceVector = ball.bounce(otherBall.getCollisionShape());
        assertTrue(bounceVector.length() > 0);
    }

    @Test
    void testPhysicsUpdate() {
        ball.physicsUpdate(params);
        assertTrue(ball.getPosition().length() > 0);
    }
}
