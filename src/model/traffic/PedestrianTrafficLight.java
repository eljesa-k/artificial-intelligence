package model.traffic;

import model.IntersectionType;

import java.util.Map;

public class PedestrianTrafficLight extends TrafficLight {

    public PedestrianTrafficLight(IntersectionType[] trafficLightsConstraint, int index, int timeFrame, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED) {
        super(trafficLightsConstraint, index, timeFrame, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
    }

    public PedestrianTrafficLight(IntersectionType[] trafficLightsConstraint, int index, int timeFrame, double priority, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED) {
        super(trafficLightsConstraint, index, timeFrame, priority, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
    }

    public PedestrianTrafficLight(IntersectionType[] trafficLightsConstraint, int index, int timeFrame) {
        super(trafficLightsConstraint, index, timeFrame);

        Map<String, Integer> timeConfig = TrafficLogicController.getTimeConfig();
        this.minTimeGreen = timeConfig.get("min_time_green_pedestrian");
        this.maxTimeRed = timeConfig.get("max_time_red_pedestrian");
        this.prefMinTimeGreen = timeConfig.get("preferred_time_green_pedestrian");
        this.prefMaxTimeRed = timeConfig.get("preferred_time_red_pedestrian");
    }
    /**
     * @inheritDoc
     */
    @Override
    public double timeConstrainScore(boolean[] sequence){
        double score =super.timeConstrainScore(sequence);
        return score;
    }
}
