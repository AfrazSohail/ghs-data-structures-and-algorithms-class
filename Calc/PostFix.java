package Calc;

import java.util.Stack;

public class PostFix {
    public static void main(String args[]) {

        String postFix1 = "43*5+";
        String postFix2 = "64-2^3*5+63-/";
        System.out.println(PostFix.evaluate(postFix1));
        System.out.println(PostFix.evaluate(postFix2));

        System.out.println(inFix2PostFix("3*(4+5)-6/(3+2)"));
        System.out.println(inFix2PostFix("6-4^2*3+5/6"));
    }

    public static int precedence(char operator) {
        switch (operator) {
            case '+', '-':
                return 1;
            case '*', '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1; // Invalid operator
        }
    }

    public static String inFix2PostFix(String infix) {
        String postfix = "";
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isDigit(c)) {
                postfix += c;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix += stack.pop();
                }
                stack.pop(); // Remove '(' from stack
            } else { // Operator
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    postfix += stack.pop();
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }

        return postfix;
    }

    public static double evaluate(String post) {
        Stack<Double> resultStack = new Stack<Double>();

        for (char c : post.toCharArray()) {
            if (Character.isDigit(c)) {
                resultStack.push((double) Character.getNumericValue(c));
            } else {
                double right = resultStack.pop();
                double left = resultStack.pop();
                switch (c) {
                    case '+':
                        resultStack.push(left + right);
                        break;
                    case '-':
                        resultStack.push(left - right);
                        break;
                    case '*':
                        resultStack.push(left * right);
                        break;
                    case '/':
                        resultStack.push(left / right);
                        break;
                    case '^':
                        resultStack.push(Math.pow(left, right));
                        break;
                    default:
                        return Double.POSITIVE_INFINITY;
                }
            }
        }

        return resultStack.pop(); // Return the final result
    }
}
