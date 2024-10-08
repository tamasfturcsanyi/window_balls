package ttm;

import java.util.ArrayList;
import java.util.List;

public class PhysicksWorld{
    public static final double GRAVITY = 10;
    public static final double SPEED = 10;
    public static final double BONCINESS = 0.5;
    public static final double MIN_DELTA = 0;
    public static final double MAX_DELTA = 0.01;


    List<Actor> bodies;

    public PhysicksWorld(){
        bodies = new ArrayList<>();
    }

    public void addBody(Actor b){
        bodies.add(b);
    }

    public void update(){
        for (Actor physicksBody : bodies) {
            if (!physicksBody.fix){
                physicksBody.physicksUpdate(physicksBody.getPos());
            }
            for (Actor otherBody : bodies) {
                if ( otherBody != physicksBody){
                    if(physicksBody.getShape().intersects(otherBody.getShape().getBounds2D())){
                        collide( physicksBody,otherBody);
                    }
                }
            }
            
        }
    }

    void collide(Actor a, Actor b){
        a.addForce(b.bounce(a).stretch(BONCINESS));
        b.addForce(a.bounce(b).stretch(BONCINESS));
    }
}