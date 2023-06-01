import model.genetic_algorithm.Population;

public class Tester {
     public static void main(String[] args) {
        double[] selectionRate = {0.05, 0.1};
        int[] populationSize = {300, 500, 1000};
        double[] mutationRate = {0.01, 0.05, 0.1};
        double[] sekuenceSize = {180, 300, 420, 600};

        int num_iterations = 5;
        double max = - 1_000_000;
        double best_mean = - 1_000_000;

        System.out.println("Number of iterations: " + num_iterations);
         System.out.println("Population size    Selection Rate    Mutation Rate    Fitness mean");
         for (int size : populationSize) {
                for (double s_rate : selectionRate) {
                    for (double m_rate : mutationRate) {
                        double mean = 0;
                        for (int i = 0; i < num_iterations; i++) {
                            Population p = new Population(size, m_rate, s_rate);

                            double score = p.performGeneticAlgorithm().getScore();
                            mean += score;
                            if(max < score) max = score;
                        }
                        System.out.printf("%-19d%-18.2f%-17.2f% ,-10.2f\n", size, s_rate, m_rate, mean/num_iterations);
                        if(best_mean < mean) best_mean = mean;
                    }
                }
            }
        }
}
