package com.zaki.mathpleb.internal.parser;

import com.zaki.mathpleb.internal.lang.PlebObject;
import com.zaki.mathpleb.internal.tree.ExpressionTree;
import com.zaki.mathpleb.internal.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private Node root;

    public Tokenizer(String input) {

        // remove all whitespace
        input = input.replaceAll("\\s+", "");
        this.root = buildExpressionTree(input);
    }

    private Node buildExpressionTree(String input) {

        List<PlebObject> plebObjects = new ArrayList<>();

        

        return new ExpressionTree().buildTree(plebObjects);
    }
}
