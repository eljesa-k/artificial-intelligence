package model.traffic.constraint.constraintMatrix;

import model.IntersectionType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Matrix {
    private IntersectionType[][] matrix;
    private String fileName;
    public IntersectionType[][] getMatrix(String fileName){
        this.fileName = fileName;
        readFromFile();
        validateMatrix();
        return matrix;
    }

    private void validateMatrix() {
        boolean symmetric = isSymmetric();
        if (!symmetric){
            fixMatrix();
        }else {
            for (int i=0; i < matrix.length; i ++) {
                matrix[i][i] = IntersectionType.ALWAYS;
            }
        }
    }

    private void fixMatrix() {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] != IntersectionType.ALWAYS) {
                matrix[i][i] = IntersectionType.ALWAYS;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                IntersectionType value1 = matrix[i][j];
                IntersectionType value2 = matrix[j][i];
                if (value1 == IntersectionType.NEVER || value2 == IntersectionType.NEVER) {
                    matrix[i][j] = IntersectionType.NEVER;
                    matrix[j][i] = IntersectionType.NEVER;
                } else if (value1 == IntersectionType.UNPREFERRED || value2 == IntersectionType.UNPREFERRED) {
                    matrix[i][j] = IntersectionType.UNPREFERRED;
                    matrix[j][i] = IntersectionType.UNPREFERRED;
                } else if (value1 == IntersectionType.ACCEPTABLE || value2 == IntersectionType.ACCEPTABLE) {
                    matrix[i][j] = IntersectionType.ACCEPTABLE;
                    matrix[j][i] = IntersectionType.ACCEPTABLE;
                } else {
                    matrix[i][j] = IntersectionType.ALWAYS;
                    matrix[j][i] = IntersectionType.ALWAYS;
                }
            }
        }
    }

    private boolean isSymmetric() {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int n = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                n++;
            }
            matrix = new IntersectionType[n][n];
            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
                int row = 0;
                while ((line = fileReader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length != n) {
                        System.out.println("Invalid number of values in line " + (row + 1) + ".");
                        return;
                    }
                    for (int col = 0; col < n; col++) {
                        matrix[row][col] = convertToIntersectionType(values[col].trim());
                    }
                    row++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    private IntersectionType convertToIntersectionType(String value) {
        switch (value) {
            case "ALWAYS":
                return IntersectionType.ALWAYS;
            case "ACCEPTABLE":
                return IntersectionType.ACCEPTABLE;
            case "UNPREFERRED":
                return IntersectionType.UNPREFERRED;
            case "NEVER":
                return IntersectionType.NEVER;
            default:
                throw new IllegalArgumentException("Invalid value: " + value);
        }
    }
}
