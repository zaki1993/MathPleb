package com.zaki.mathpleb.internal.tree;

import com.zaki.mathpleb.internal.lang.PlebObject;
import com.zaki.mathpleb.internal.lang.PlebOperator;
import com.zaki.mathpleb.internal.lang.operator.Precedence;

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

                if (isOperator(current)) {
                    // if operator put it on top
                    Node operator = new Node(current, true);

                    // current top Node in the stack
                    if (!stack.isEmpty()) {
                        Node top = stack.pop();
                        if (isOperator(top.getPlebObject())) {
                            // find right most operator
                            Node toWorkWith = null;
                            if (top.getRight() != null) {
                                if (top.getRight().getLeft() != null) {
                                    toWorkWith = findRightMostNode(top);
                                } else {
                                    toWorkWith = top;
                                }
                            } else {
                                toWorkWith = top;
                            }

                            Precedence toWorkWithPrecedence = ((PlebOperator) toWorkWith.getPlebObject()).getPrecedence();
                            Precedence currentPrecedence = ((PlebOperator) current).getPrecedence();

                            // right most has greatest precedence, so it should stay like this
                            if (toWorkWithPrecedence.compareTo(currentPrecedence) >= 0) {
                                // add the current as a parent of the top
                                if (toWorkWith.getLeft() == null) {
                                    toWorkWith.setLeft(operator);
                                } else {
                                    toWorkWith.setRight(operator);
                                }
                                stack.push(top);
                            } else {
                                // current operator has greater precedence, so swap with top one
                                Node topRight = toWorkWith.getRight();
                                operator.setLeft(topRight);
                                toWorkWith.setRight(operator);
                                stack.push(toWorkWith);
                            }
                        } else {
                            operator.setLeft(top);
                            stack.push(operator);
                        }
                    } else {
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
                            Node toInsert = findLeftMostNode(top);
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

    private Node findLeftMostNode(Node top) {

        Node result = null;

        if (top != null) {
            Node left = top.getLeft();
            Node right = top.getRight();

            if ((left == null && right != null) || (left != null && right == null)) {
                result = top;
            }
            if (left != null && right != null) {
                Node leftSearch = findLeftMostNode(left);
                result = leftSearch == null ? findLeftMostNode(right) : leftSearch;
            }
        }

        return result;
    }

    private Node findRightMostNode(Node top) {

        Node result = null;

        if (top != null) {
            Node left = top.getLeft();
            Node right = top.getRight();

            if ((left == null && right != null) || (left != null && right == null)) {
                result = top;
            }
            if (left != null && right != null) {
                Node rightSearch = findRightMostNode(right);
                result = rightSearch == null ? findRightMostNode(left) : rightSearch;
            }
        }

        return result;
    }
}
