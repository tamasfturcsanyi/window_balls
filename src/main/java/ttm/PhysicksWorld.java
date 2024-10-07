package ttm;

import java.util.ArrayList;
import java.util.List;

public class PhysicksWorld{
    public static final double GRAVITY = 10;
    public static final double SPEED = 10;

    List<PhysicksBody> bodies;

    public PhysicksWorld(){
        bodies = new ArrayList<>();
    }

    public void addBody(PhysicksBody b){
        bodies.add(b);
    }

    public void update(){
        for (PhysicksBody physicksBody : bodies) {
            if (!physicksBody.fix){
                physicksBody.physicksUpdate(physicksBody.getPos());
            }
        }
    }
}