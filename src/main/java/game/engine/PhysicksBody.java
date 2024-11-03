package game.engine;

public abstract class PhysicksBody {
    long previousTime;

    //pos is modified by velocity every frame
    Vector2D velocity;

    //velocity is modified by force/weight every frame
    Vector2D force;

    Vector2D externalForces;

    double mass = 1;

    boolean fix = false;

    PhysicksBody(double mass, boolean fix){
        this.mass = mass;
        this.fix = fix;

        Vector2D nulla = new Vector2D(0,0);
        force = nulla;
        externalForces = nulla;
        velocity = nulla;
        previousTime = 0;
    }


    public double getMass(){
        return mass;
    }

    abstract Vector2D getPos(); 
    
    abstract void setPos(Vector2D newPos);

    public Vector2D getExternalForce(){
        return externalForces;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void addForce(Vector2D f){
        externalForces = externalForces.add(f);
    }

    abstract void calculateForces(SimulationParameters params);

    abstract void calculateNewVelocity(double delta, SimulationParameters params);

    abstract void calculateNewPosition(double delta);
    
    //calculates new position, applies forces, moves Body
    abstract void physicksUpdate(SimulationParameters params);
}
