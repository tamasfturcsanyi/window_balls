package ttm;

import java.util.ArrayList;
import java.util.List;

public class PhysicksWorld{
    public static final double GRAVITY = 10;
    public static final double SPEED = 10;
    public static final double BONCINESS = 20;


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

        Vector2D diff = b.getPos().diff(a.getPos());
        a.addForce(diff.stretch(BONCINESS));

        diff = a.getPos().diff(b.getPos());
        b.addForce(diff.stretch(BONCINESS));


    }
}