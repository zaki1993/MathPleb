package com.zaki.mathpleb.internal.lang.operator.impl;

import com.zaki.mathpleb.internal.lang.PlebPrimitive;
import com.zaki.mathpleb.internal.lang.operator.Operator;
import com.zaki.mathpleb.internal.lang.operator.Precedence;
import com.zaki.mathpleb.internal.lang.operator.exception.InvalidArgumentException;
import com.zaki.mathpleb.internal.lang.operator.exception.InvalidArgumentNumberException;

public class TgOperator extends Operator {

    private static final String SIGN = "tg";

    public TgOperator() {
        super(Precedence.MIDDLE, SIGN);
    }

    @Override
    protected boolean validateArguments(PlebPrimitive... args) {

        if (args.length != 1) {
            throw new InvalidArgumentNumberException();
        }
        PlebPrimitive a = args[0];

        if (a == null) {
            throw new InvalidArgumentException();
        }

        return true;
    }

    @Override
    protected PlebPrimitive consumeInternal(PlebPrimitive... args) {

        PlebPrimitive result;

        // If the parameters are not expression, then sum them otherwise result the result as a expression
        if (!isExpression(args)) {
            double value = Double.parseDouble(args[0].getValue());
            result = new PlebPrimitive(Math.sin(value) / Math.cos(value));
        } else {
            result = new PlebPrimitive(SIGN + "(" + args[0] + ")");
        }

        return result;
    }

    @Override
    public PlebPrimitive[] getInternalArguments(String signature) {

        String arg = signature.substring(signature.indexOf("(") + 1, signature.lastIndexOf(")"));
        PlebPrimitive[] args = {new PlebPrimitive(arg)};
        return args;
    }
}