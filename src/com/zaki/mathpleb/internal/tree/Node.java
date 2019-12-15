package com.zaki.mathpleb.internal.tree;

import com.zaki.mathpleb.internal.lang.PlebObject;
import com.zaki.mathpleb.internal.lang.PlebOperator;
import com.zaki.mathpleb.internal.lang.PlebPrimitive;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {

    private boolean isOperator;
    private PlebObject obj;
    private Node left;
    private Node right;
    private Node parent;

    public Node(PlebObject obj, boolean isOperator) {
        this.obj = obj;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.isOperator = isOperator;
    }

    public Node(PlebObject obj) {
        this(obj, false);
    }

    public void setLeft(Node left) {
        this.left = left;
        left.setParent(this);
    }

    public void setRight(Node right) {
        this.right = right;
        right.setParent(this);
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return this.parent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return isOperator == node.isOperator &&
                obj.equals(node.obj) &&
                left.equals(node.left) &&
                right.equals(node.right) &&
                parent.equals(node.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOperator, obj, left, right, parent);
    }
}
