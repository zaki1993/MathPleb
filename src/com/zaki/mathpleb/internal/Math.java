package com.zaki.mathpleb.internal;

public class Math {

    public static Number sum(Number a, Number b) {

        Number result = null;

        if (a instanceof Double || b instanceof Double) {
            result = a.doubleValue() + b.doubleValue();
        } else if (a instanceof Float || b instanceof Float) {
            result = a.floatValue() + b.floatValue();
        } else {
            result = a.longValue() + b.longValue();
        }

        return result;
    }

    public static Number subtract(Number a, Number b) {

        Number result = null;

        if (a instanceof Double || b instanceof Double) {
            result = a.doubleValue() - b.doubleValue();
        } else if (a instanceof Float || b instanceof Float) {
            result = a.floatValue() - b.floatValue();
        } else {
            result = a.longValue() - b.longValue();
        }

        return result;
    }

    public static Number multiply(Number a, Number b) {

        Number result = null;

        if (a instanceof Double || b instanceof Double) {
            result = a.doubleValue() * b.doubleValue();
        } else if (a instanceof Float || b instanceof Float) {
            result = a.floatValue() * b.floatValue();
        } else {
            result = a.longValue() * b.longValue();
        }

        return result;
    }

    // TODO handle cases of infinity and 0
    public static Number devide(Number a, Number b) {

        Number result = null;

        if (a instanceof Double || b instanceof Double) {
            result = a.doubleValue() / b.doubleValue();
        } else if (a instanceof Float || b instanceof Float) {
            result = a.floatValue() / b.floatValue();
        } else {
            result = a.longValue() / b.longValue();
        }

        return result;
    }
}
