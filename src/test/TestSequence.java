package test;

import model.IntersectionType;
import model.traffic.Sequence;
import model.traffic.TrafficLight;
import model.traffic.VehicleTrafficLight;

public class TestSequence {
    public static void main(String[] args) {

        int MIN_TIME_GREEN = 2;
        int MAX_TIME_RED = 3;
        int PREF_MIN_TIME_GREEN  = 3;
        int PREF_MAX_TIME_RED =  1;
        int timeFrameLength = 10;

        boolean[][] sequence = {
                {false, true, false},
                {true, false, true},
                {true, true, true},
                {false, true, false},
                {true, true, false},
                {false, false, true},
                {true, true, false}
        };

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

        Sequence sequence1 = new Sequence(trafficLights, sequence);
        System.out.println(sequence1.getScore());
    }
}
