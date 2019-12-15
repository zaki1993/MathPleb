package com.zaki.mathpleb.internal.lang.operator.impl;

import com.zaki.mathpleb.internal.lang.PlebPrimitive;
import com.zaki.mathpleb.internal.lang.math.MathPleb;
import com.zaki.mathpleb.internal.lang.operator.Operator;
import com.zaki.mathpleb.internal.lang.operator.Precedence;

import java.util.function.Function;

public class CosOperator extends Operator {

    public CosOperator() {
        super(Precedence.LOW, "cos");
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
        return MathPleb::cos;
    }
}
