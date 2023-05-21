package model.genetic_algorithm;

import model.traffic.Sequence;

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
        int n = mutatedSequence.length;
        int m = mutatedSequence[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double random = Math.random();
                if (random < 0.1) {
                    mutatedSequence[i][j] = !mutatedSequence[i][j];
                }
            }
        }

        return new Chromosome(new Sequence(getTrafficLights(), mutatedSequence));
    }

    /**TODO: Implement crossover() method*/
    public Chromosome[] crossover(Chromosome chromosome){
        boolean[][] sequence1 = getSequence();
        boolean[][] sequence2 = chromosome.getSequence();
        int n = sequence1.length;
        int m = sequence1[0].length;

        // Create child chromosome arrays
        boolean[][] child1 = new boolean[n][m];
        boolean[][] child2 = new boolean[n][m];

        // Iterate over each gene in the chromosomes
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double random = Math.random();
                if (random < 0.9) {
                    child1[i][j] = sequence1[i][j];
                    child2[i][j] = sequence2[i][j];
                } else {
                    child1[i][j] = sequence2[i][j];
                    child2[i][j] = sequence1[i][j];
                }
            }
        }

        return new Chromosome[] {
                new Chromosome(new Sequence(getTrafficLights(), child1)),
                new Chromosome(new Sequence(getTrafficLights(), child2))
        };
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
