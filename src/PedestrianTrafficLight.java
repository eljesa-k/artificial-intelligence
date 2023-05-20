public class PedestrianTrafficLight extends TrafficLights{

    public PedestrianTrafficLight(boolean isGreen, int timeFrame, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED) {
        super(isGreen, timeFrame, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
    }

    @Override
    public double getScore(int[] sequence) {
        return 0;
    }
}
