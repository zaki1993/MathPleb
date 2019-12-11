package com.zaki.mathpleb.internal;

import com.zaki.mathpleb.internal.lang.PlebObject;
import com.zaki.mathpleb.internal.lang.PlebObjectBuilder;
import com.zaki.mathpleb.internal.tree.ExpressionTree;
import com.zaki.mathpleb.internal.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    // TODO return tree
    public String parse(String input) {

        List<String> inputList = parseInput(input);
        List<PlebObject> plebInput = convertInputToPlebList(inputList);
        System.out.println("Input is: " + plebInput);

        Node expressionTree = new ExpressionTree().buildTree(plebInput);

        return String.valueOf(expressionTree.compute());
    }

    public List<String> parseInput(String input) {

        input = input.replaceAll("\\s+", "");
        String[] inputArr = input.split("((?<=\\+|\\-|\\*|\\/)|(?=\\+|\\-|\\*|\\/))");

        List inputList = new ArrayList<>();
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
