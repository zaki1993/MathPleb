package com.zaki.mathpleb.internal.lang.operator;

import com.zaki.mathpleb.internal.lang.PlebPrimitive;
import com.zaki.mathpleb.internal.lang.operator.exception.InvalidArgumentException;
import com.zaki.mathpleb.internal.lang.operator.exception.InvalidArgumentNumberException;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class Operator {

    private static final String ONLY_DIGITS_REGEXP = "(\\-?\\d*\\.?\\d+)";

    private Precedence precedence;

    private String sign;

    public Operator(Precedence precedence, String sign) {
        this.precedence = precedence;
        this.sign = sign;
    }

    protected abstract PlebPrimitive consumeInternal(PlebPrimitive... args);

    protected abstract boolean validateArguments(PlebPrimitive... args);

    protected abstract Object getFunction();

    public Precedence getPrecedence() {
        return this.precedence;
    }

    public String getSign() {
        return this.sign;
    }

    protected boolean isExpression(PlebPrimitive... args){
        return !Stream.of(args).allMatch(arg -> arg.getValue().matches(ONLY_DIGITS_REGEXP));
    }

    protected final boolean validateArguments_v1(PlebPrimitive... args) {

        if (args.length != 1) {
            throw new InvalidArgumentNumberException();
        }
        PlebPrimitive a = args[0];

        if (a == null) {
            throw new InvalidArgumentException();
        }

        return true;
    }

    protected final boolean validateArguments_v2(PlebPrimitive... args) {

        if (args.length != 2) {
            throw new InvalidArgumentNumberException();
        }
        PlebPrimitive a = args[0];
        PlebPrimitive b = args[1];

        if (a == null || b == null) {
            throw new InvalidArgumentException();
        }

        return true;
    }

    protected final PlebPrimitive consumeInternal_v2(PlebPrimitive... args) {

        PlebPrimitive result;

        if (!isExpression(args)) {
            BiFunction function = (BiFunction) getFunction();
            result = new PlebPrimitive(function.apply(args[0].getValueAsDouble(), args[1].getValueAsDouble()));
        } else {
            result = new PlebPrimitive(args[0] + " " + sign + " " + args[1]);
        }

        return result;
    }

    protected final PlebPrimitive consumeInternal_v1(PlebPrimitive... args) {

        PlebPrimitive result;

        // If the parameters are not expression, then sum them otherwise result the result as a expression
        if (!isExpression(args)) {
            Function function = (Function) getFunction();
            result = new PlebPrimitive(function.apply(Double.parseDouble(args[0].getValue())));
        } else {
            result = new PlebPrimitive(sign + "(" + args[0] + ")");
        }

        return result;
    }

    protected final PlebPrimitive[] getInternalArgumentsDefault(String signature) {

        String arg = signature.substring(signature.indexOf("(") + 1, signature.lastIndexOf(")"));
        PlebPrimitive[] args = { new PlebPrimitive(arg) };

        return args;
    }

    /*
        Method used to get arguments, if the operator has internal arguments
     */
    public PlebPrimitive[] getInternalArguments(String signature) {
        // TODO
        return null;
    }

    public PlebPrimitive consume(PlebPrimitive... args) {

        boolean areArgumentsValid = validateArguments(args);

        if (!areArgumentsValid) {
            // TODO throw error
        }

        return consumeInternal(args);
    }

}
