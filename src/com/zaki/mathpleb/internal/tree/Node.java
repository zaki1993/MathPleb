package com.zaki.mathpleb.internal.tree;

import com.zaki.mathpleb.internal.lang.PlebObject;
import com.zaki.mathpleb.internal.lang.PlebOperator;
import com.zaki.mathpleb.internal.lang.PlebPrimitive;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private boolean isOperator;
    private PlebObject obj;
    private Node left;
    private Node right;

    public Node(PlebObject obj, boolean isOperator) {
        this.obj = obj;
        this.left = null;
        this.right = null;
        this.isOperator = isOperator;
    }

    public Node(PlebObject obj) {
        this(obj, false);
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public PlebObject getPlebObject() {
        return obj;
    }

    public PlebObject compute() {
        if (isOperator) {
            return computeOperatorInternal((PlebOperator) obj);
        }
        return obj;
    }

    private PlebObject computeOperatorInternal(PlebOperator operator) {

        PlebPrimitive leftResult = left != null ? (PlebPrimitive) left.compute() : null;
        PlebPrimitive rightResult = right != null ? (PlebPrimitive) right.compute() : null;

        List<PlebPrimitive> args = new ArrayList<>();
        if (leftResult != null) {
            args.add(leftResult);
        }
        if (rightResult != null) {
            args.add(rightResult);
        }

        return operator.consume(args.toArray(new PlebPrimitive[args.size()]));
    }
}
