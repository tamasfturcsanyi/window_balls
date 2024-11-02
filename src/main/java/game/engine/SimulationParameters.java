package game.engine;

public class SimulationParameters{
    double gravity = 10;
    double speed = 10;
    double bounciness = 0.5;
    double energyLeftover = 0.999998;
    double speedLimit = 500;

    public SimulationParameters(){
    }

    public SimulationParameters(double gravity , double speed, double bounciness, double energyLeftover, double speedLimit){
        this.gravity = gravity;
        this.speed = speed;
        this.bounciness = bounciness;
        this.energyLeftover = energyLeftover;
        this.speedLimit = speedLimit;
    }
    
}