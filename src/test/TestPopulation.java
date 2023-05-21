package test;

import model.IntersectionType;
import model.genetic_algorithm.Chromosome;
import model.genetic_algorithm.Population;
import model.traffic.Sequence;
import model.traffic.TrafficLight;
import model.traffic.VehicleTrafficLight;

public class TestPopulation {
    public static void main(String[] args) {
        // Create a sample sequence
        IntersectionType[] intersectionTypes1 = {IntersectionType.ALWAYS, IntersectionType.NEVER};
        VehicleTrafficLight trafficLight1 = new VehicleTrafficLight(
                intersectionTypes1, 0, true, 10, 5, 20, 8, 15
        );

        IntersectionType[] intersectionTypes2 = {IntersectionType.ACCEPTABLE, IntersectionType.UNPREFERRED};
        VehicleTrafficLight trafficLight2 = new VehicleTrafficLight(
                intersectionTypes2, 1, false, 10, 3, 15, 6, 12
        );
        boolean[][] sequenceE = new boolean[10][2];
        Sequence sequence = new Sequence(
                new TrafficLight[]{trafficLight1, trafficLight2},
                new boolean[][]{
                        {true, false, true},
                        {false, true, false},
                        {true, true, false}
                }
        );
//        Population population = new Population(10);
//        population.initializePopulation(sequence, 10);
//        System.out.println("Initial Population:");
//        displayPopulation(population);
//        population.performCrossover(0.8);
//        System.out.println("\nPopulation after Crossover:");
//        displayPopulation(population);
//        population.performMutation(0.1);
//        System.out.println("\nPopulation after Mutation:");
//        displayPopulation(population);

    }


    private static void displayPopulation(Population population) {
        for (int i = 0; i < population.getPopulation().size(); i++) {
            Chromosome chromosome = population.getPopulation().get(i);
            System.out.println("Chromosome " + i + ": \n " + chromosome + " \nchromosome score: " + chromosome.getScore());
        }
    }
}

