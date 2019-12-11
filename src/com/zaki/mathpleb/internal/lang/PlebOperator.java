package com.zaki.mathpleb.internal.lang;

import com.zaki.mathpleb.internal.lang.operator.Operator;
import com.zaki.mathpleb.internal.lang.operator.Precedence;

public class PlebOperator extends PlebObject {

    private final Operator operator;

    public PlebOperator(String value, Operator operator) {

        super(value.contains("(") && value.contains(")") ?
                PlebObjectBuilder.buildInternal(value.substring(value.indexOf("(") + 1, value.lastIndexOf(")"))) :
                value);
        this.operator = operator;
    }

    public Precedence getPrecedence() {
        return operator.getPrecedence();
    }

    // TODO revise
    public PlebObject consume(PlebPrimitive... args) {

        if (args == null || args.length == 0) {
            // try to consume the value of the operator in this case
            Object value = getValue();
            PlebPrimitive arg;
            if (value instanceof PlebOperator) {
                arg = new PlebPrimitive(((PlebOperator) value).consume());
            } else {
                arg = new PlebPrimitive(value.toString());
            }
            args = new PlebPrimitive[] {arg};
        }

        return operator.consume(args);
    }

    @Override
    public String toString() {
        return operator.getSign();
    }
}
