package test;

import model.IntersectionType;
import model.traffic.constraint.constraintMatrix.Matrix;

public class TestMatrix {
    public static void main(String[] args) {
        Matrix matrix = new Matrix();

        IntersectionType[][] matrix1 = matrix.getMatrix("matrix.csv");
        if (matrix1 != null) {
            System.out.println("model.matrix.Matrix from matrix.csv:");
            printMatrix(matrix1);
        }

        System.out.println();
        IntersectionType[][] matrix2 = matrix.getMatrix("non_symmetric_matrix.csv");
        if (matrix2 != null) {
            System.out.println("model.matrix.Matrix from non_symmetric_matrix.csv:");
            printMatrix(matrix2);
        }
    }

    private static void printMatrix(IntersectionType[][] matrix) {
        for (IntersectionType[] row : matrix) {
            for (IntersectionType value : row) {
                System.out.print(value.toString() + " ");
            }
            System.out.println();
        }
    }
}

