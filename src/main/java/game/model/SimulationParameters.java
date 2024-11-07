package game.model;

public class SimulationParameters{
    double gravity = 10;
    double simulationSpeed = 10;
    double energyLeftover = 0.99;
    double speedLimit = 300;

    public SimulationParameters(){
    }

    public SimulationParameters(double gravity , double speed, double energyLeftover, double speedLimit){
        this.gravity = gravity;
        this.simulationSpeed = speed;
        this.energyLeftover = energyLeftover;
        this.speedLimit = speedLimit;
    }

    public double getGravity() {
        return gravity;
    }

    public double getEnergyLeftover() {
        return energyLeftover;
    }

    public double getSimulationSpeed() {
        return simulationSpeed;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }
    
}