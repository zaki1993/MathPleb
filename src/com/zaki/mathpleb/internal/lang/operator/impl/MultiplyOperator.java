package com.zaki.mathpleb.internal.lang.operator.impl;

import com.zaki.mathpleb.internal.lang.PlebPrimitive;
import com.zaki.mathpleb.internal.lang.operator.Operator;
import com.zaki.mathpleb.internal.lang.operator.Precedence;
import com.zaki.mathpleb.internal.lang.operator.exception.InvalidArgumentException;
import com.zaki.mathpleb.internal.lang.operator.exception.InvalidArgumentNumberException;

public class MultiplyOperator extends Operator {

    private static final String SIGN = "*";

    public MultiplyOperator() {
        super(Precedence.HIGHEST, SIGN);
    }

    @Override
    protected boolean validateArguments(PlebPrimitive... args) {

        if (args.length != 2) {
            throw   new InvalidArgumentNumberException();
        }
        PlebPrimitive a = args[0];
        PlebPrimitive b = args[1];

        if (a == null || b == null) {
            throw new InvalidArgumentException();
        }

        return true;
    }

    @Override
    protected PlebPrimitive consumeInternal(PlebPrimitive... args) {

        PlebPrimitive result;

        if (!isExpression(args)) {
            result = new PlebPrimitive(args[0].getValueAsDouble() * args[1].getValueAsDouble());
        } else {
            result = new PlebPrimitive(args[0] + " " + SIGN + " " + args[1]);
        }

        return result;
    }
}
