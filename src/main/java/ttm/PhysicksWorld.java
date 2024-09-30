package ttm;

import java.util.ArrayList;
import java.util.List;

public class PhysicksWorld{
    public static final double GRAVITY = 10;
    public static final double SPEED = 10;

    List<PhysicksBody> bodies;

    PhysicksWorld(){
        bodies = new ArrayList<>();
    }

    void addBody(PhysicksBody b){
        bodies.add(b);
    }

    void update(){
        for (PhysicksBody physicksBody : bodies) {
            physicksBody.physicksUpdate(physicksBody.getPos());
        }
    }
}