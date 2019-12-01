package com.zaki.mathpleb.internal.impl;

import com.zaki.mathpleb.internal.impl.lang.PlebObject;
import com.zaki.mathpleb.internal.impl.lang.PlebOperator;
import com.zaki.mathpleb.internal.impl.lang.PlebPrimitive;

public enum Operator {
    PLUS("+", Precedence.LOWEST) {
        public PlebPrimitive consumeInternal(PlebPrimitive a, PlebPrimitive b) {
            return new PlebPrimitive(a.getValueAsLong() + b.getValueAsLong());
        }
    },
    MINUS("-", Precedence.LOWEST) {
        @Override
        PlebPrimitive consumeInternal(PlebPrimitive a, PlebPrimitive b) {
            return new PlebPrimitive(a.getValueAsLong() - b.getValueAsLong());
        }
    },
    MULTIPLY("*", Precedence.MIDDLE) {
        @Override
        PlebPrimitive consumeInternal(PlebPrimitive a, PlebPrimitive b) {
            return new PlebPrimitive(a.getValueAsLong() * b.getValueAsLong());
        }
    },
    DIVIDE("/", Precedence.MIDDLE) {
        @Override
        PlebPrimitive consumeInternal(PlebPrimitive a, PlebPrimitive b) {
            return new PlebPrimitive(a.getValueAsLong() / b.getValueAsLong());
        }
    },

    UNDEFINED {
        @Override
        PlebPrimitive consumeInternal(PlebPrimitive a, PlebPrimitive b) {
            return null;
        }
    };

    private String operatorSymbol;

    private Precedence precedence;

    abstract PlebPrimitive consumeInternal(PlebPrimitive a, PlebPrimitive b);

    Operator(String operatorSymbol, Precedence precedence) {
        this.operatorSymbol = operatorSymbol;
        this.precedence = precedence;
    }

    Operator() {
        this.operatorSymbol = null;
        this.precedence = null;
    }

    public static Operator getFromString(String candidateOperator) {

        Operator result = UNDEFINED;

        for (Operator op : values()) {
            if (op.operatorSymbol != null && op.operatorSymbol.equals(candidateOperator)) {
                result = op;
                break;
            }
        }

        return result;
    }

    public Precedence getPrecedence() {
        return this.precedence;
    }

    public PlebPrimitive consume(PlebPrimitive a, PlebPrimitive b) {
        return consumeInternal(a, b);
    }

    public String getSymbol() {
        return this.operatorSymbol;
    }
}
