public class Sequence implements Node{
    TrafficLights[] trafficLights;
    IntersectionType[][] tlConstraints;
    private int score;

    public Sequence(TrafficLights[] trafficLights, IntersectionType[][] tlConstraints) {
        this.trafficLights = trafficLights;
        this.tlConstraints = tlConstraints;

        this.calculateScore();
    }

    @Override
    public double getScore() {
        return score;
    }

    private void calculateScore(){

        // TODO: this.score = calculated score
    }
}
