package com.zaki.mathpleb.internal.impl.lang;

import com.zaki.mathpleb.internal.impl.Operator;
import com.zaki.mathpleb.internal.impl.Precedence;

public class PlebOperator extends PlebObject {

    private final Operator operator;

    public PlebOperator(String value, Operator operator) {
        super(value);
        this.operator = operator;
    }

    public Precedence getPrecedence() {
        return operator.getPrecedence();
    }

    public PlebObject consume(PlebPrimitive a, PlebPrimitive b) {
        return operator.consume(a, b);
    }

    @Override
    public String toString() {
        return operator.getSymbol();
    }
}
