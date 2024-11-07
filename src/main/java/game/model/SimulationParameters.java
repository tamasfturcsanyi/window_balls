package game.model;

public class SimulationParameters{
    double gravity = 10;
    double simulationSpeed = 10;
    double bounceEnergyRemaining = 0.9;
    double speedLimit = 200;
    double generalEnergyLoss = 0.0001;

    public SimulationParameters(){
    }

    public SimulationParameters(double gravity , double speed, double bounceEnergyRemaining, double speedLimit, double generalEnergyLoss){
        this.gravity = gravity;
        this.simulationSpeed = speed;
        this.bounceEnergyRemaining = bounceEnergyRemaining;
        this.speedLimit = speedLimit;
        this.generalEnergyLoss = generalEnergyLoss;
    }

    public double getGravity() {
        return gravity;
    }

    public double getBounceEnergyRemaining() {
        return bounceEnergyRemaining;
    }

    public double getSimulationSpeed() {
        return simulationSpeed;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }
    
    public double getGeneralEnergyLoss() {
        return generalEnergyLoss;
    }
}