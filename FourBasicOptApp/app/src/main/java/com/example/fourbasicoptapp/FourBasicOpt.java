package com.example.fourbasicoptapp;

/**
 * For the four basic arithmetic operations.
 * Class can be used to add(addition), subtract(subtraction), divide(division) and
 * multiply(multiplication).
 */
public class FourBasicOpt {
    public double add(double x, double y) {
        return x + y;
    }

    public double subtract(double x, double y) {
        return x - y;
    }

    public double divide(double x, double y) {
        if (y == 0) {
            return 0;
        }
        return x / y;
    }

    public double multiply(double x, double y) {
        return x * y;
    }
}
