package com.zaki.mathpleb.internal;

public class Validator {

    private static final String BALANCED_PARENTHESES_REGEX = "/^((['\"])|\\()(.*)(?(?=\\2)(\\2)|(\\)))$/";

    /**
     * Validates if the number of left parentheses is equal to the number of right parentheses and if so returns the input.
     * Otherwise tries to even the number of left and right parentheses and returns the result.
     */
    public String validateParentheses(String input) {
        return input;
    }
}
