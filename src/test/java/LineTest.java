import org.junit.jupiter.api.Test;

import ttm.Line;

import static org.junit.jupiter.api.Assertions.*;

public class LineTest {
    @Test
    void testConstructor(){
        Line line = new Line(100,200,100,300);

        assertEquals(100, line.getShape().getP1().getX());
        assertEquals(200, line.getShape().getP1().getY());

        assertEquals(100,line.getShape().getP2().getX());
        assertEquals(300,line.getShape().getP2().getY());
    }
}
