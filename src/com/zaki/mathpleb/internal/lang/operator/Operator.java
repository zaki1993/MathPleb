package com.zaki.mathpleb.internal.lang.operator;

import com.zaki.mathpleb.internal.lang.PlebPrimitive;

import java.util.stream.Stream;

public abstract class Operator {

    private static final String ONLY_DIGITS_REGEXP = "(\\-?\\d*\\.?\\d+)";

    private Precedence precedence;

    private String sign;

    public Operator(Precedence precedence, String sign) {
        this.precedence = precedence;
        this.sign = sign;
    }

    public Precedence getPrecedence() {
        return this.precedence;
    }

    public String getSign() {
        return this.sign;
    }

    public PlebPrimitive consume(PlebPrimitive... args) {
        boolean areArgumentsValid = validateArguments(args);

        if (!areArgumentsValid) {
            // TODO throw error
        }

        return consumeInternal(args);
    }

    protected boolean isExpression(PlebPrimitive... args){
        return !Stream.of(args).allMatch(arg -> arg.getValue().matches(ONLY_DIGITS_REGEXP));
    }

    protected abstract boolean validateArguments(PlebPrimitive... args);

    protected abstract PlebPrimitive consumeInternal(PlebPrimitive... args);

    /*
        Method used to get arguments, if the operator has internal arguments
     */
    public PlebPrimitive[] getInternalArguments(String signature) {
        // TODO
        return null;
    }
}
