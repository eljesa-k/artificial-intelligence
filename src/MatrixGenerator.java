import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MatrixGenerator {
    private static final IntersectionType[] VALUES = IntersectionType.values();
    private static final Random RANDOM = new Random();

    public static void generateNonSymmetricCSV(String fileName, int size) {
        try (FileWriter writer = new FileWriter(fileName)) {
            IntersectionType[][] matrix = new IntersectionType[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    IntersectionType value = VALUES[RANDOM.nextInt(VALUES.length)];
                    matrix[i][j] = value;
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    writer.write(matrix[i][j].toString());
                    if (j < size - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void generateSymmetricCSV(String fileName, int size) {
        try (FileWriter writer = new FileWriter(fileName)) {
            IntersectionType[][] matrix = new IntersectionType[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j <= i; j++) {
                    IntersectionType value = VALUES[RANDOM.nextInt(VALUES.length)];
                    matrix[i][j] = value;
                    matrix[j][i] = value;
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    writer.write(matrix[i][j].toString());
                    if (j < size - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
//        String fileName = "matrix.csv";
//        int size = 16;
//        generateSymmetricCSV(fileName, size);
//        System.out.println("CSV file generated: " + fileName);
//
//        String fileName = "non_symmetric_matrix.csv";
//        int size = 16;
//        generateNonSymmetricCSV(fileName, size);
//        System.out.println("CSV file generated: " + fileName);
    }
}

