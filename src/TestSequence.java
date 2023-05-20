public class TestSequence {
    public static void main(String[] args) {
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

        TrafficLights[] trafficLights = new TrafficLights[3];

        Sequence sequence1 = new Sequence(trafficLights, constraints, sequence);
        System.out.println(sequence1.getScore());
    }
}
