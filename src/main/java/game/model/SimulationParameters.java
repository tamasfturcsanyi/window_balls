package game.model;

public class SimulationParameters{
    Vector2D gravity = new Vector2D(0,10);
    double simulationSpeed = 1;
    double bounceEnergyRemaining = 0.6;
    double speedLimit = 200;
    double generalEnergyLoss = 0.0001;

    boolean shakeable = true;

    public SimulationParameters(){
    }

    public SimulationParameters(Vector2D gravity , double speed, double bounceEnergyRemaining, double speedLimit, double generalEnergyLoss, boolean shakeable){
        this.gravity = gravity;
        this.simulationSpeed = speed;
        this.bounceEnergyRemaining = bounceEnergyRemaining;
        this.speedLimit = speedLimit;
        this.generalEnergyLoss = generalEnergyLoss;
        this.shakeable = shakeable;
    }

    public Vector2D getGravity() {
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

    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }
    
    public double getGeneralEnergyLoss() {
        return generalEnergyLoss;
    }

    public boolean getShakeable(){
        return shakeable;
    }
}