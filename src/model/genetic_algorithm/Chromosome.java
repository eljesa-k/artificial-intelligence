package model.genetic_algorithm;

import model.traffic.Sequence;

import java.util.Arrays;
import java.util.List;

public class Chromosome extends Sequence {
    /**
     * @inheritDoc
     */
    private List<Boolean> chromosome;
    /**
     * Konstruktori i klasës Chromosome.
     * Krijon një instancë të re të kromozomit duke marrë një sekuencë të dhënë.
     *
     * @param sequence Sekuenca e dritave të trafikut dhe të gjendjeve të tyre ntë intervale kohore.
     */
    public Chromosome(Sequence sequence) {
        super(sequence.getTrafficLights(), sequence.getSequence());
    }
    /**
     * Mutacioni i kromozomit.
     * Realizon një mutacion në sekuencën e kromozomit.
     * Çdo gjen mund të ndryshohet me një rate prej 0.1.
     *
     * @return Kromozomi i mutuar.
     */
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
    /**
     * Crossover (krijuar i re) i kromozomit.
     * Realizon crossover (krijuar i re) midis dy kromozomeve.
     * Crossover ndodh me një shans prej 0.9, duke shkëmbyer gjenet midis kromozomeve.
     *
     * @param chromosome Kromozomi tjetër për të realizuar crossover.
     * @return Dy kromozomet e reja të fituara pas crossover-it.
     */
    public Chromosome[] crossover(Chromosome chromosome){
        boolean[][] sequence1 = getSequence();
        boolean[][] sequence2 = chromosome.getSequence();
        int n = sequence1.length;
        int m = sequence1[0].length;
        boolean[][] child1 = new boolean[n][m];
        boolean[][] child2 = new boolean[n][m];
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
    /**
     * Metoda toString.
     * Kthen një string që përfaqëson sekuencën e kromozomit.
     *
     * @return Stringu që përfaqëson sekuencën e kromozomit.
     */
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
