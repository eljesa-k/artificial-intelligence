public class PedestrianTrafficLight extends TrafficLights{

    public PedestrianTrafficLight(boolean isGreen, int timeOpen, int timeToWait, int[] sequence) {
        super(isGreen, timeOpen, timeToWait, sequence);
    }

    @Override
    public double getScore() {
        return 0;
    }
}
