public class VehicleTrafficLight extends TrafficLights{

    private double trafficCoeff=0;

    public VehicleTrafficLight(boolean isGreen, int timeOpen, int timeToWait, int[] sequence, double trafficCoeff) {
        super(isGreen, timeOpen, timeToWait, sequence);
        this.trafficCoeff = trafficCoeff;
    }

    @Override
    public double getScore() {
        return 0;
    }
}
