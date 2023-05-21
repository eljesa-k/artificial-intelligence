package model.traffic;

import model.IntersectionType;
import model.traffic.TrafficLights;

import java.util.ArrayList;

public class PedestrianTrafficLight extends TrafficLights {

    public PedestrianTrafficLight(IntersectionType[] trafficLightsConstraint, int index, boolean isGreen, int timeFrame, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED) {
        super(trafficLightsConstraint, index,isGreen, timeFrame, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
    }

    public PedestrianTrafficLight(IntersectionType[] trafficLightsConstraint, int index, boolean isGreen, int timeFrame, double priority, int MIN_TIME_GREEN, int MAX_TIME_RED, int PREF_MIN_TIME_GREEN, int PREF_MAX_TIME_RED) {
        super(trafficLightsConstraint, index,isGreen, timeFrame, priority, MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED);
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
