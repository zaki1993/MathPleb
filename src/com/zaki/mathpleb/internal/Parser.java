package com.zaki.mathpleb.internal;

import com.zaki.mathpleb.internal.lang.PlebObject;
import com.zaki.mathpleb.internal.lang.PlebObjectBuilder;
import com.zaki.mathpleb.internal.tree.ExpressionTree;
import com.zaki.mathpleb.internal.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String SINGLE_CHAR_OPERATOR_REGXP = "\\+|\\-|\\*|\\/|\\^|\\%";

    public Node parse(String input) {

        List<String> inputList = parseInput(input);
        List<PlebObject> plebInput = convertInputToPlebList(inputList);
        System.out.println("Input is: " + plebInput);

        return new ExpressionTree().buildTree(plebInput);
    }

    public String compute(Node tree) {
        return String.valueOf(tree.compute());
    }

    public List<String> parseInput(String input) {

        input = input.replaceAll("\\s+", "");
        String[] inputArr = input.split("((?<=" + SINGLE_CHAR_OPERATOR_REGXP + ")|(?=" + SINGLE_CHAR_OPERATOR_REGXP + "))");

        List<String> inputList = new ArrayList<>();
        // Search for ( followed by number or letter and concatenate until we find )
        boolean foundOpenParentheses = false;
        String formulaToConstruct = "";
        for (int i = 0; i < inputArr.length; i++) {

            if (foundOpenParentheses) {
                formulaToConstruct += inputArr[i];
                if (inputArr[i].contains(")")) {
                    // Check if we match the number of left and right parentheses and if so, we are done
                    // otherwise continue to add until we match them
                    if (areParenthesesBalanced(formulaToConstruct)) {
                        foundOpenParentheses = false;
                        inputList.add(formulaToConstruct);
                        formulaToConstruct = "";
                    }
                }
            } else if (inputArr[i].contains("(") && !inputArr[i].contains(")")) {
                foundOpenParentheses = true;
                formulaToConstruct += inputArr[i];
            } else {
                inputList.add(inputArr[i]);
            }
        }

        // check whether we have situations like "1 * -1"
        // we have to parse [-, 1] as '-1'
        // check whether if we have '-', the previous string is operator and is single character operator
        // in that case concatenate the next string with the '-'
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).equals("-") &&
                    i - 1 >= 0 &&
                    inputList.get(i - 1).matches(SINGLE_CHAR_OPERATOR_REGXP) &&
                    i + 1 < inputList.size()) {
                String combinedOperator = inputList.get(i) + inputList.get(i + 1);
                // remove the two consecutive characters
                inputList.remove(i);
                inputList.remove(i);
                inputList.add(i, combinedOperator);
            }
        }

        return inputList;
    }

    public List<PlebObject> convertInputToPlebList(List<String> input) {

        List result = new ArrayList(input.size());

        for (String in : input) {
            result.add(PlebObjectBuilder.build(in));
        }

        return result;
    }

    private boolean areParenthesesBalanced(String formula) {

        int balance = 0;

        for (int i = 0; i < formula.length(); i++) {
            if (formula.charAt(i) == '(') {
                balance++;
            }
            if (formula.charAt(i) == ')') {
                balance--;
            }
        }

        return balance == 0;
    }
}
