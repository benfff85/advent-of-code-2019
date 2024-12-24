package com.adventofcode.year2024.day13;

import org.apache.commons.math3.util.Pair;

public class LinearEquationSolver {

    public static Pair<Long, Long> solveEquations(double a, double b, double c, double d, double s, double t) {
        // Step 1: Calculate the determinant
        double determinant = a * d - b * c;
        if (determinant == 0) {
            return null;
        }

        // Step 2: Calculate the inverse matrix
        double invA = d / determinant;
        double invB = -b / determinant;
        double invC = -c / determinant;
        double invD = a / determinant;

        // Step 3: Multiply the inverse matrix by the constants S and T
        double x = invA * s + invB * t;
        double y = invC * s + invD * t;

        // Step 4: Check if x and y are whole numbers
        if (isWholeNumber(x) && isWholeNumber(y)) {
            return new Pair<>(Math.round(x), Math.round(y));
        } else {
            return null;
        }

    }

    // Helper method to check if a number is a whole number
    private static boolean isWholeNumber(double value) {
        double tolerance = 0.0001;
        return Math.abs(value - Math.round(value)) < tolerance;
    }

}
