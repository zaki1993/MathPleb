package com.zaki.mathpleb.internal.lang.operator.impl;

import com.zaki.mathpleb.internal.lang.PlebPrimitive;
import com.zaki.mathpleb.internal.lang.math.MathPleb;
import com.zaki.mathpleb.internal.lang.operator.Operator;
import com.zaki.mathpleb.internal.lang.operator.Precedence;

import java.util.function.BiFunction;

public class MultiplyOperator extends Operator {

    public MultiplyOperator() {
        super(Precedence.HIGH, "*");
    }

    @Override
    protected PlebPrimitive consumeInternal(PlebPrimitive... args) {
        return consumeInternal_v2(args);
    }

    @Override
    protected boolean validateArguments(PlebPrimitive... args) {
        return validateArguments_v2(args);
    }

    @Override
    protected BiFunction<Double, Double, Double> getFunction() {
        return MathPleb::multiply;
    }
}
