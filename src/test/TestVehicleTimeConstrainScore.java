package test;

import model.IntersectionType;
import model.traffic.TrafficLight;
import model.traffic.VehicleTrafficLight;

public class TestVehicleTimeConstrainScore {
    /**
     * @param args - testim
     */
    public static void main(String[] args) {
        int MIN_TIME_GREEN = 2;
        int MAX_TIME_RED = 3;
        int PREF_MIN_TIME_GREEN  = 3;
        int PREF_MAX_TIME_RED =  1;
        int timeFrameLength = 10;

        IntersectionType[][] constraints = {
                {IntersectionType.ALWAYS, IntersectionType.ACCEPTABLE, IntersectionType.NEVER},
                {IntersectionType.ACCEPTABLE, IntersectionType.ALWAYS, IntersectionType.UNPREFERRED},
                {IntersectionType.NEVER, IntersectionType.UNPREFERRED, IntersectionType.ALWAYS}
        };

        TrafficLight[] trafficLights = {
                new VehicleTrafficLight(constraints[0], 0, true, timeFrameLength,
                        MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED),
                new VehicleTrafficLight(constraints[1], 1, true, timeFrameLength,
                        MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED),
                new VehicleTrafficLight(constraints[2], 2, true, timeFrameLength,
                        MIN_TIME_GREEN, MAX_TIME_RED, PREF_MIN_TIME_GREEN, PREF_MAX_TIME_RED)
        };
        boolean[][] sequence = {
                {false, true, false},
                {true, false, true},
                {true, true, true},
                {false, true, false},
                {true, true, false},
                {false, false, true},
                {true, true, false}
        };
        System.out.println(trafficLights[0].timeConstrainScore(sequence[0]));
    }
}
