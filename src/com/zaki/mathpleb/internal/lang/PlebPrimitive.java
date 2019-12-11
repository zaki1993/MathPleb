package com.zaki.mathpleb.internal.lang;

public class PlebPrimitive extends PlebObject {

    public PlebPrimitive(String value) {
        super(value);
    }

    public PlebPrimitive(Object value) {
        super(String.valueOf(value));
    }

    @Override
    public String toString() {
        return getValue();
    }

    public long getValueAsLong() {
        return Long.parseLong(getValue());
    }

    public double getValueAsDouble() {
        return Double.parseDouble(getValue());
    }

    public String getValue() {
        return (String) super.getValue();
    }
}
