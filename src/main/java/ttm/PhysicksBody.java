package ttm;



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

        Vector2D nulla = new Vector2D();
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

    

    void calculateForces(){
        force = new Vector2D();
        //apply gravity
        force = force.add(new Vector2D(0, PhysicksWorld.GRAVITY * mass));

        force = force.add(externalForces);
        externalForces = new Vector2D(0,0);
    }

    void calculateNewVelocity(double delta){
        Vector2D acceleration = new Vector2D(force.getX()/mass,force.getY()/mass);
        velocity = velocity.add(acceleration.stretch(delta));
    }

    void calculateNewPosition(double delta, Vector2D oldPos){
        this.setPos( oldPos.add(velocity.stretch(delta)));
    }
    
    //calculates new position, applies forces, moves Body
    void physicksUpdate(Vector2D oldPos){
        long currentTime = System.nanoTime();

        //if theres no previous time: skip
        if(previousTime == 0){
            previousTime = currentTime;
            return;
        }
        long nanoDelta = currentTime - previousTime;

        //convert to seconds
        double delta = nanoDelta * 0.000000001 * PhysicksWorld.SPEED;
        
        calculateForces();
        calculateNewVelocity(delta);
        calculateNewPosition(delta, oldPos);
        

        previousTime = currentTime;
    }

    public void addForce(Vector2D f){
        externalForces = externalForces.add(f);
    }

    
}
