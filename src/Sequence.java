import java.util.Arrays;
import java.util.stream.IntStream;

public class Sequence implements Node{
    TrafficLights[] trafficLights;
    IntersectionType[][] tlConstraints;
    boolean[][] sequence;
    private double score;

    public Sequence(TrafficLights[] trafficLights, IntersectionType[][] tlConstraints, boolean[][] sequence) {
        this.trafficLights = trafficLights;
        this.tlConstraints = tlConstraints;
        this.sequence = sequence;

        this.calculateScore();
    }

    @Override
    public double getScore() {
        return score;
    }

    private void calculateScore(){
        this.score =  Arrays.stream(sequence)
                        .map(timeFrame -> IntStream.range(0, trafficLights.length)
                        .mapToObj(i -> checkTrafficLightCompatibility(tlConstraints[i], timeFrame, i))
                        .reduce(0.0, Double::sum))
                        .reduce(0.0, Double::sum);

//        for (int i = 0; i < sequence.length; i++) {
//            for (int j = 0; j < tlConstraints.length; j++) {
//                    this.score += checkTrafficLightCompatibility(tlConstraints[j], sequence[i], j);
//            }
//            System.out.println();
//        }
    }

    private double checkTrafficLightCompatibility(IntersectionType[] type, boolean[] seq, int index){

        return IntStream.range(0, type.length)
                .filter(i -> i != index)
                .mapToObj(i -> seq[index] ? checkCompatibilityForOpen(type[i], seq[i]) : checkCompatibilityForClosed(type[i], seq[i]))
                .reduce(0.0, Double::sum);

//        double sumPoints = 0;
//        if(seq[index]) {
//            for (int i = 0; i < type.length; i++) {
//                if (index != i)
//                    sumPoints += checkCompatibilityForOpen(type[i], seq[i]);
//            }
//        }
//        else
//            for (int i = 0; i < type.length; i++) {
//                if(index != i)
//                    sumPoints += checkCompatibilityForClosed(type[i], seq[i]);
//            }
//        System.out.println(sumPoints);
//        return sumPoints;
    }

    private double checkCompatibilityForOpen(IntersectionType type, boolean affirmative){
        int hard = 100_000;
        int soft = 500;
        int cool = 10;

        switch (type){
            case NEVER -> {
                return affirmative ? - hard : cool;
            }
            case UNPREFERRED-> {
                return affirmative ? - soft  : soft;
            }
            case ACCEPTABLE -> {
                return affirmative ? soft : - soft;
            }
            case ALWAYS -> {
                return affirmative ? cool : - hard;
            }
            default -> {
                return 0;
            }
        }
    }
    private double checkCompatibilityForClosed(IntersectionType type, boolean affirmative){
        int hard = 100_000;
        int soft = 500;
        int cool = 10;

        switch (type){
            case NEVER -> {
                return affirmative ? soft : - soft;
            }
            case UNPREFERRED-> {
                return affirmative ? soft : - cool;
            }
            case ACCEPTABLE -> {
                return affirmative ? - soft : cool;
            }
            case ALWAYS -> {
                return affirmative ? - hard : cool;
            }
            default -> {
                return 0;
            }
        }
    }

}
