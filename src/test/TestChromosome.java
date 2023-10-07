package test;

import model.IntersectionType;
import model.genetic_algorithm.Chromosome;
import model.traffic.Sequence;
import model.traffic.TrafficLight;
import model.traffic.VehicleTrafficLight;

public class TestChromosome{
    public static void main(String[] args) {
        IntersectionType[] intersectionTypes1 = {IntersectionType.ALWAYS, IntersectionType.NEVER};
        VehicleTrafficLight trafficLight1 = new VehicleTrafficLight(
                intersectionTypes1, 0, 10, 5, 20, 8, 15
        );

        IntersectionType[] intersectionTypes2 = {IntersectionType.ACCEPTABLE, IntersectionType.UNPREFERRED};
        VehicleTrafficLight trafficLight2 = new VehicleTrafficLight(
                intersectionTypes2, 1, 10, 3, 15, 6, 12
        );
        boolean[][] sequence = new boolean[10][2];
        Chromosome chromosome = new Chromosome(
                new Sequence(new TrafficLight[]{trafficLight1, trafficLight2}, sequence)
        );
        System.out.println("Before Mutation:");
        System.out.println("Chromosome Score: " + chromosome.getScore());
        Chromosome mutatedChromosome = chromosome.mutate();
        System.out.println("After Mutation:");
        System.out.println("Mutated Chromosome Score: " + mutatedChromosome.getScore());

        boolean[][] parent1Sequence = {
                {true, true, false, false, true},
                {false, true, true, true, false},
                {true, false, true, false, true}
        };

        boolean[][] parent2Sequence = {
                {false, true, false, true, false},
                {true, false, false, false, true},
                {false, true, false, true, false}
        };

        Chromosome parent1 = new Chromosome(new Sequence(new TrafficLight[]{trafficLight1, trafficLight2}, parent1Sequence));
        Chromosome parent2 = new Chromosome(new Sequence(new TrafficLight[]{trafficLight1, trafficLight2}, parent2Sequence));
        System.out.println("Parent 1 Score: " + parent1.getScore());
        System.out.println("Parent 2 Score: " + parent2.getScore());

        Chromosome[] children = parent1.crossover(parent2);
        System.out.println("Child 1 Score: " + children[0].getScore());
        System.out.println("Child 2 Score: " + children[1].getScore());
    }
}


