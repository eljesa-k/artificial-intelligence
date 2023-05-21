package model.genetic_algorithm;

import model.genetic_algorithm.Chromosome;
import model.traffic.Sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population{
    private List<Chromosome> population;
    private int populationSize;

    public Population(int populationSize) {
        this.populationSize = populationSize;
        this.population = new ArrayList<>();
    }

    public void initializePopulation(Sequence sequence, int initialPopulationSize) {
        for (int i = 0; i < initialPopulationSize; i++) {
            Chromosome chromosome = createRandomChromosome(sequence);
            population.add(chromosome);
        }
    }

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
public Chromosome selectIndividual() {
        Random random = new Random();
        int randomIndex = random.nextInt(population.size());
        return population.get(randomIndex);
    }

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

    public void performMutation(double mutationRate) {
        for (Chromosome chromosome : population) {
            if (Math.random() < mutationRate) {
                Chromosome mutatedChromosome = chromosome.mutate();
                population.set(population.indexOf(chromosome), mutatedChromosome);
            }
        }
    }
    public List<Chromosome> getPopulation() {
        return population;
    }

    public void setPopulation(List<Chromosome> population) {
        this.population = population;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }
}
