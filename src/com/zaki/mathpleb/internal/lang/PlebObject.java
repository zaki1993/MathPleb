package com.zaki.mathpleb.internal.lang;

public abstract class PlebObject {

    private Object value;

    public PlebObject(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
