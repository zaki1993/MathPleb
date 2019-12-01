package com.zaki.mathpleb.internal.impl.lang;

public class PlebPrimitive extends PlebObject {

    public PlebPrimitive(String value) {
        super(value);
    }

    public PlebPrimitive(long value) {
        super(String.valueOf(value));
    }

    @Override
    public String toString() {
        return value;
    }

    public long getValueAsLong() {
        return Long.parseLong(value);
    }
}
