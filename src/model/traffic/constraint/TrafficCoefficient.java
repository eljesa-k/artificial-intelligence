package model.traffic.constraint;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TrafficCoefficient {
    double[] values ;
//    public static void main(String[] args) {
//        String filePath = "random_numbers.csv";
//        int numberOfNumbers = 12;
//
//        try {
//            generateRandomNumbersCSV(filePath, numberOfNumbers);
//            System.out.println("CSV file generated successfully.");
//        } catch (IOException e) {
//            System.out.println("An error occurred while generating the CSV file.");
//            e.printStackTrace();
//        }
//        String filePath = "random_numbers.csv";
//        TrafficCoefficient trafficCoefficient = new TrafficCoefficient();
//        double[] values = trafficCoefficient.getValuesFromFile(filePath);
//        System.out.println("Values from the file:");
//        for (double value : values) {
//            System.out.println(value);
//        }
//    }
    /**
     * Lexon vlerat nga një file CSV dhe i kthen si një varg me vlera të tipit double.
     *
     * @param fileName Emri i file-it nga ku do të lexohen vlerat.
     * @return Vargu me vlerat e lexuara nga file-i.
     */
    public double[] getValuesFromFile(String fileName) {
        int numberOfValues = 12;
        double[] values = new double[numberOfValues];
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null && count < numberOfValues) {
                String[] valueStrings = line.split(",");
                for (String valueString : valueStrings) {
                    try {
                        double value = Double.parseDouble(valueString.trim());
                        values[count++] = value;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid value format in the file. Skipping the line.");
                    }
                }
            }

            if (count < numberOfValues) {
                System.out.println("Insufficient number of values in the file. Only " + count + " values were found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return values;
    }
    /**
     * Gjeneron një file CSV me vlera të rastësishme të tipit double.
     *
     * @param filePath        File name ku do të krijohet file-i CSV.
     * @param numberOfNumbers Numri i vlerave të rastësishme që do të gjenerohen.
     * @throws IOException Në rastin e ndonjë gabimi gjatë shkrimin në file.
     */
    public static void generateRandomNumbersCSV(String filePath, int numberOfNumbers) throws IOException {
        Random random = new Random();

        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < numberOfNumbers; i++) {
                double randomNumber = random.nextDouble();
                writer.write(String.valueOf(randomNumber));
                if (i < numberOfNumbers - 1) {
                    writer.write(",");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}

