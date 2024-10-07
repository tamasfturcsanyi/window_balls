package ttm;

abstract class Actor extends PhysicksBody implements Visual {
    public Actor(double mass, boolean fix){
        super(mass, fix);
    }
}
