package com.zaki.mathpleb.internal;

import com.zaki.mathpleb.internal.impl.ExpressionTree;
import com.zaki.mathpleb.internal.impl.Node;
import com.zaki.mathpleb.internal.impl.Operator;
import com.zaki.mathpleb.internal.impl.lang.PlebObject;
import com.zaki.mathpleb.internal.impl.lang.PlebOperator;
import com.zaki.mathpleb.internal.impl.lang.PlebPrimitive;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    // TODO return tree
    public String parse(String input) {

        // TODO validate input
        String[] inputArr = input.split(" ");

        List<PlebObject> plebInput = convertInputToPlebList(inputArr);
        System.out.println("Input is: " + plebInput);

        Node expressionTree = new ExpressionTree().buildTree(plebInput);

        return String.valueOf(expressionTree.compute());
    }

    private List<PlebObject> convertInputToPlebList(String[] input) {

        List result = new ArrayList(input.length);

        for (int i = 0; i < input.length; i++) {
            Operator op = Operator.getFromString(input[i]);

            // We have operator, otherwise we have some other symbol
            if (op != Operator.UNDEFINED) {
                result.add(new PlebOperator(input[i], op));
            } else {
                result.add(new PlebPrimitive(input[i]));
            }
        }

        return result;
    }
}
