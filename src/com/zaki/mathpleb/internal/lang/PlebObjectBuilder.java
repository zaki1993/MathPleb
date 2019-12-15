package com.zaki.mathpleb.internal.lang;

import com.zaki.mathpleb.internal.Parser;
import com.zaki.mathpleb.internal.lang.operator.Operator;
import com.zaki.mathpleb.internal.lang.operator.OperatorBuilder;
import com.zaki.mathpleb.internal.tree.ExpressionTree;
import com.zaki.mathpleb.internal.tree.Node;

import java.util.List;

public class PlebObjectBuilder {

    private static final String ONLY_DIGITS_REGEXP = "(\\-?\\d*\\.?\\d+)";

    private PlebObjectBuilder() {

    }

    public static PlebObject build(String sign) {

        // Check if input is expression
        // if so do early consumption
        Operator op = OperatorBuilder.build(sign);
        return op != null ? new PlebOperator(sign, op) : new PlebPrimitive(sign);
    }

    public static PlebObject buildInternal(String sign) {

        Parser p = new Parser();

        PlebObject result;

        List<String> parsedSign = p.parseInput(sign);
        if (parsedSign.size() > 1) {
            List<PlebObject> plebObjects = p.convertInputToPlebList(parsedSign);

            // Check if input is expression
            // if so do early consumption
            if (plebObjects.size() > 1 && isExpression(plebObjects)) {
                Node internalExpTree = new ExpressionTree().buildTree(plebObjects);
                result = internalExpTree.compute();
            } else {
                result = plebObjects.get(0);
            }
        } else {
            result = new PlebPrimitive(sign);
        }

        return result;
    }
    private static boolean isExpression(List plebObjects){
        return !plebObjects.stream().allMatch(arg -> arg.toString().matches(ONLY_DIGITS_REGEXP));
    }
}
