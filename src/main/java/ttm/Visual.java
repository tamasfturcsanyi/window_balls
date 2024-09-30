package ttm;

import java.awt.Rectangle;
import java.awt.Shape;

public interface Visual {
    

    Shape getShape();
    void updateVisuals(Rectangle windowBounds);
}
