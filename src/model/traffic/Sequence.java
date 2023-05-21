package model.traffic;

import model.traffic.Node;
import model.traffic.TrafficLights;

import java.util.Arrays;

public class Sequence implements Node {
    TrafficLights[] trafficLights;
    private boolean[][] sequence;
    private double score;

    /**
     * Modelon nje moster qe mund te jete zgjidhje e problemit
     * @param trafficLights vargu i semaforeve
     * @param sequence vargu i gjendjeve te semaforeve ne secilin interval kohor
     */
    public Sequence(TrafficLights[] trafficLights, boolean[][] sequence) {
        this.trafficLights = trafficLights;
        this.sequence = sequence;

        this.score = this.calculateScore();
    }

    /**
     * Kthen fitnesin e monstres
     * @return score
     */
    @Override
    public double getScore() {
        return score;
    }

    /**
     * Llogarit fitnesin e kesaj sekuence si shume e fitneseve te secilit semafor
     * @return score
     */
    private double calculateScore(){
        return Arrays.stream(this.trafficLights)
                .map(item -> item.getScore(sequence))
                .reduce(0.0, Double::sum);
    }

    public boolean[][] getSequence() {
        return sequence;
    }

    public TrafficLights[] getTrafficLights() {
        return trafficLights;
    }
}
