package com.zaki.mathpleb.internal.lang.math;

public class MathPleb {

    public static Double sum(Double a, Double b) {
        return a + b;
    }

    public static Double subtract(Double a, Double b) {
        return a - b;
    }

    public static Double multiply(Double a, Double b) {
        return a * b;
    }

    public static Double divide(Double a, Double b) {
        return a / b;
    }

    public static Double cos(Double a) {
        return Math.cos(a);
    }

    public static Double sin(Double a) {
        return Math.sin(a);
    }

    public static Double tan(Double a) {
        return Math.tan(a);
    }

    public static Double cotan(Double a) {
        return 1 / tan(a);
    }

    public static Double modulo(Double a, Double b) {
        return a % b;
    }

    public static Double abs(Double a) {
        return Math.abs(a);
    }
}
