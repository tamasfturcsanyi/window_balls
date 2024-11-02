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

    

    void calculateForces(SimulationParameters params){
        force = new Vector2D();
        //apply gravity
        force = force.add(new Vector2D(0, params.gravity * mass));

        force = force.add(externalForces);
        externalForces = new Vector2D(0,0);
    }

    void calculateNewVelocity(double delta, SimulationParameters params){
        Vector2D acceleration = force.stretch(1/mass);

        //apply acceleration
        velocity = velocity.add(acceleration.stretch(delta));

        //friction
        velocity = velocity.stretch(params.energyLeftover);

        //speed limit
        if(velocity.length() > params.speedLimit){
            velocity = velocity.stretch(0.9);
        }

        
    }

    void calculateNewPosition(double delta){
        this.setPos(getPos().add(velocity.stretch(delta)));
    }
    
    //calculates new position, applies forces, moves Body
    void physicksUpdate(SimulationParameters params){
        long currentTime = System.nanoTime();

        //if theres no previous time: skip
        if(previousTime == 0){
            previousTime = currentTime;
            return;
        }
        long nanoDelta = currentTime - previousTime;

        //convert to seconds
        double delta = nanoDelta * 0.000000001 * params.speed;
        
        calculateForces(params);
        calculateNewVelocity(delta, params);
        calculateNewPosition(delta);
        

        previousTime = currentTime;
    }

    public void addForce(Vector2D f){
        externalForces = externalForces.add(f);
    }

    
}
