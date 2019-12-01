package com.zaki.mathpleb.internal.impl;

import com.zaki.mathpleb.internal.impl.lang.PlebObject;
import com.zaki.mathpleb.internal.impl.lang.PlebOperator;

import java.util.List;
import java.util.Stack;

public class ExpressionTree {

    private boolean isOperator(PlebObject object) {
        return object instanceof PlebOperator;
    }

    /**
     * Returns root of constructed tree for given infix expression.
     *
     * Example:
     *
     * Infix expression: 1 + 2 * 3
     * Output:
     *         (*)
     *       /  \
     *     (+)   3
     *   /  \
     * 1     2
     */
    public Node buildTree(List<PlebObject> postFixExpression) {

        Stack<Node> stack = new Stack();
        if (postFixExpression != null && postFixExpression.size() > 0) {
            for (int i = 0; i < postFixExpression.size(); i++) {
                PlebObject current = postFixExpression.get(i);

                // TODO check for ( or ) or [ or ] or { or }
                if (isOperator(current)) {
                    // if operator put it on top
                    Node operator = new Node(current, true);

                    // current top Node in the stack
                    Node top = stack.pop();
                    if (isOperator(top.getPlebObject())) {
                        Precedence topPrecedence = ((PlebOperator) top.getPlebObject()).getPrecedence();
                        Precedence currentPrecedence = ((PlebOperator) current).getPrecedence();

                        // Top operator has greater precedence, so it should stay like this
                        if (topPrecedence.compareTo(currentPrecedence) >= 0) {
                            // add the current as a parent of the top
                            operator.setLeft(top);
                            stack.push(operator);
                        } else {
                            // current operator has greater precedence, so swap with top one
                            Node topRight = top.getRight();
                            operator.setLeft(topRight);
                            top.setRight(operator);
                            stack.push(top);
                        }
                    } else {
                        operator.setLeft(top);
                        stack.push(operator);
                    }
                } else {
                    Node node = new Node(current);
                    if (!stack.isEmpty()) {
                        // check if the top has left child and if not, push this as a left, otherwise push it as a right child
                        Node top = stack.peek();
                        if (top.getLeft() == null) {
                            top.setLeft(node);
                        } else if (top.getRight() == null) {
                            top.setRight(node);
                        } else {
                            // otherwise find the correct position for this node
                            // search where we have Node with only 1 child
                            Node toInsert = findNode(top);
                            if (toInsert.getLeft() == null) {
                                toInsert.setLeft(node);
                            } else {
                                toInsert.setRight(node);
                            }
                        }
                    } else {
                        // No elements in stack just push it
                        stack.push(node);
                    }
                }
            }
        }

        return stack.peek();
    }

    private Node findNode(Node top) {

        Node result = null;

        if (top != null) {
            Node left = top.getLeft();
            Node right = top.getRight();

            if ((left == null && right != null) || (left != null && right == null)) {
                result = top;
            }
            if (left != null && right != null) {
                Node leftSearch = findNode(left);
                result = leftSearch == null ? findNode(right) : leftSearch;
            }
        }

        return result;
    }
}
