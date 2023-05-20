public class VehicleTrafficLight extends TrafficLights{

    private double trafficCoeff;

    public VehicleTrafficLight(boolean isGreen, int timeFrame, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED, double trafficCoeff) {
        super(isGreen, timeFrame, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
        this.trafficCoeff = trafficCoeff;
    }

    public VehicleTrafficLight(boolean isGreen, int timeFrame, double priority, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED, double trafficCoeff) {
        super(isGreen, timeFrame, priority, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
        this.trafficCoeff = trafficCoeff;
    }

    @Override
    public double getScore(int[] sequence) {
        return 0;
    }
}
