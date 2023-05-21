package model.genetic_algorithm;

import model.genetic_algorithm.Chromosome;
import model.traffic.Sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population{
    private List<Chromosome> population;
    private int populationSize;
    /**
     * Konstruktori i klasës Population.
     * Krijon një popullsi të re me madhësinë e caktuar.
     *
     * @param populationSize Madhësia e popullsisë.
     */
    public Population(int populationSize) {
        this.populationSize = populationSize;
        this.population = new ArrayList<>();
    }
    /**
     * Inicializimi i popullsisë.
     * Krijon një popullsi fillestare të përbërë nga kromozome të rastësishme.
     *
     * @param sequence              Sekuenca e dritave të trafikut për të krijuar kromozome të rastësishme.
     * @param initialPopulationSize Madhësia e popullsisë fillestare.
     */
    public void initializePopulation(Sequence sequence, int initialPopulationSize) {
        for (int i = 0; i < initialPopulationSize; i++) {
            Chromosome chromosome = createRandomChromosome(sequence);
            population.add(chromosome);
        }
    }
    /**
     * Krijimi i kromozomit të rastësishëm.
     * Krijon një kromozom të rastësishëm duke marrë sekuencën fillestare dhe vendos gjenet e kromozomit
     * në mënyrë të rastësishme.
     *
     * @param sequence Sekuenca fillestare e dritave të trafikut.
     * @return Kromozomi i rastësishëm.
     */
    private Chromosome createRandomChromosome(Sequence sequence) {
        boolean[][] originalSequence = sequence.getSequence();
        boolean[][] randomSequence = new boolean[originalSequence.length][];
        for (int i = 0; i < originalSequence.length; i++) {
            randomSequence[i] = originalSequence[i].clone();
            for (int j = 0; j < originalSequence[i].length; j++) {
                randomSequence[i][j] = Math.random() < 0.5; // Set gene randomly to true or false
            }
        }
        return new Chromosome(new Sequence(sequence.getTrafficLights(), randomSequence));
    }
    /**
     * Zgjedhja e një individit të rastësishëm.
     * Zgjedh një individ të rastësishëm nga popullsia aktuale.
     *
     * @return Individi i zgjedhur.
     */
    public Chromosome selectIndividual() {
        Random random = new Random();
        int randomIndex = random.nextInt(population.size());
        return population.get(randomIndex);
    }
    /**
     * Realizimi i crossover-it (krijuarit të re) në popullsi.
     * Realizon crossover (krijuarit të re) midis kromozomeve të popullsisë.
     * Crossover ndodh me një shans prej crossoverRate, duke prodhuar të dy fëmijët e ri.
     *
     * @param crossoverRate Shansi i crossover-it për çiftin e kromozomeve.
     */
    public void performCrossover(double crossoverRate) {
        List<Chromosome> offspring = new ArrayList<>();
        while (offspring.size() < populationSize) {
            Chromosome parent1 = selectIndividual();
            Chromosome parent2 = selectIndividual();
            if (Math.random() < crossoverRate) {
                Chromosome[] children = parent1.crossover(parent2);
                offspring.add(children[0]);
                offspring.add(children[1]);
            } else {
                offspring.add(parent1);
                offspring.add(parent2);
            }
        }
        population = offspring;
    }
    /**
     * Realizimi i mutacionit në popullsi.
     * Realizon mutacion në secilin kromozom të popullsisë.
     * Mutacion ndodh me një shans prej mutationRate.
     *
     * @param mutationRate Shansi i mutacionit për secilin kromozom.
     */
    public void performMutation(double mutationRate) {
        for (Chromosome chromosome : population) {
            if (Math.random() < mutationRate) {
                Chromosome mutatedChromosome = chromosome.mutate();
                population.set(population.indexOf(chromosome), mutatedChromosome);
            }
        }
    }
    /**
     * Merr popullsinë aktuale.
     *
     * @return Popullsia aktuale.
     */
    public List<Chromosome> getPopulation() {
        return population;
    }
    /**
     * Vendos popullsinë e re.
     *
     * @param population Popullsia e re.
     */
    public void setPopulation(List<Chromosome> population) {
        this.population = population;
    }
    /**
     * Merr madhësinë e popullsisë.
     *
     * @return Madhësia e popullsisë.
     */
    public int getPopulationSize() {
        return populationSize;
    }
    /**
     * Vendos madhësinë e popullsisë.
     *
     * @param populationSize Madhësia e re e popullsisë.
     */
    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }
}
