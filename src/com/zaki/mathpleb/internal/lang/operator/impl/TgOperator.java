package com.zaki.mathpleb.internal.lang.operator.impl;

import com.zaki.mathpleb.internal.lang.PlebPrimitive;
import com.zaki.mathpleb.internal.lang.math.MathPleb;
import com.zaki.mathpleb.internal.lang.operator.Operator;
import com.zaki.mathpleb.internal.lang.operator.Precedence;

import java.util.function.Function;

public class TgOperator extends Operator {

    public TgOperator() {
        super(Precedence.LOW, "tg");
    }

    @Override
    protected boolean validateArguments(PlebPrimitive... args) {
        return validateArguments_v1(args);
    }

    @Override
    protected PlebPrimitive consumeInternal(PlebPrimitive... args) {
        return consumeInternal_v1(args);
    }

    @Override
    public PlebPrimitive[] getInternalArguments(String signature) {
        return getInternalArgumentsDefault(signature);
    }

    @Override
    protected Function<Double, Double> getFunction() {
        return MathPleb::tan;
    }
}
