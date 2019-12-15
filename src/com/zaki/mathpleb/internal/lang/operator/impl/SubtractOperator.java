package com.zaki.mathpleb.internal.lang.operator.impl;

import com.zaki.mathpleb.internal.lang.PlebPrimitive;
import com.zaki.mathpleb.internal.lang.math.MathPleb;
import com.zaki.mathpleb.internal.lang.operator.Operator;
import com.zaki.mathpleb.internal.lang.operator.Precedence;

import java.util.function.BiFunction;

public class SubtractOperator extends Operator {

    public SubtractOperator() {
        super(Precedence.LOWEST, "-");
    }

    @Override
    protected PlebPrimitive consumeInternal(PlebPrimitive... args) {
        try {
            return consumeInternal_v2(args);
        } catch (Exception e) {
            // handle the case when we have negation only
            return new PlebPrimitive("-" + args[0].toString());
        }
    }

    @Override
    protected boolean validateArguments(PlebPrimitive... args) {
        try {
           return validateArguments_v1(args);
        } catch (Exception e) {
           return validateArguments_v2(args);
        }
    }

    @Override
    protected BiFunction<Double, Double, Double> getFunction() {
        return MathPleb::subtract;
    }
}
