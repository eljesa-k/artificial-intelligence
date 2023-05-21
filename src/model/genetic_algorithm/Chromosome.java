package model.genetic_algorithm;

import model.traffic.Sequence;
import model.traffic.TrafficLights;

import java.util.Arrays;
import java.util.List;

public class Chromosome extends Sequence {
    /**
     * @inheritDoc
     */
    private List<Boolean> chromosome;
    public Chromosome(Sequence sequence) {
        super(sequence.getTrafficLights(), sequence.getSequence());
    }
    /**TODO : Implement mutate() method*/
    public Chromosome mutate(){
        boolean[][] mutatedSequence = getSequence().clone();
        int randomIndex = (int) (Math.random() * mutatedSequence.length);
        for (int i = 0; i < mutatedSequence[randomIndex].length; i++) {
            mutatedSequence[randomIndex][i] = !mutatedSequence[randomIndex][i];
        }
        return new Chromosome(new Sequence(getTrafficLights(), mutatedSequence));
    }

    /**TODO: Implement crossover() method*/
    public Chromosome[] crossover(Chromosome chromosome){
        boolean[][] sequence1 = getSequence();
        boolean[][] sequence2 = chromosome.getSequence();

        int randomIndex = (int) (Math.random() * sequence1.length);

        boolean[][] child1 = new boolean[sequence1.length][];
        boolean[][] child2 = new boolean[sequence2.length][];
        for (int i = 0; i < randomIndex; i++) {
            child1[i] = sequence1[i].clone();
            child2[i] = sequence2[i].clone();
        }
        for (int i = randomIndex; i < sequence1.length; i++) {
            child1[i] = sequence2[i].clone();
            child2[i] = sequence1[i].clone();
        }
        return new Chromosome[]{new Chromosome(new Sequence(getTrafficLights(), child1)),
                new Chromosome(new Sequence(getTrafficLights(), child2))};
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean[][] sequence = getSequence();
        for (boolean[] row : sequence) {
            sb.append(Arrays.toString(row)).append(System.lineSeparator());
        }
        return sb.toString();
    }


}
