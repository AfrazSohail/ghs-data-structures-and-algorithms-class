// import java.util.Stack;

// import WeBareBears.Bear;

// /**
// * Utility class for stack operations and bracket checking.
// */
// public class Stacked {
// /**
// * Main method to demonstrate stack and bracket operations.
// *
// * @param args Command-line arguments (not used).
// */
// public static void main(String[] args) {
// Stack<Integer> hats = new Stack<Integer>();

// hats.push(11);
// hats.push(10);
// hats.push(10);
// hats.push(11);
// // System.out.println(hats.peek());
// // System.out.println("original stack:");
// // System.out.println(hats);
// // Stack<Integer> temp = new Stack<Integer>();
// // while(!hats.isEmpty()) {
// // System.out.println(hats.peek());
// // temp.push(hats.pop());
// // }
// // while(!temp.isEmpty()) {
// // hats.push(temp.pop());
// // System.out.println("after popping: " + hats.peek());
// // }

// // System.out.println(IsPalindrome(hats));
// // System.out.println(hats);

// // System.out.println(CheckBrackets("a+(b*c)-[2-a]"));
// CustomDeque<Bear> dequeBears = new CustomDeque<Bear>(20);
// dequeBears.addFront(new Bear("Yogi", 0, 0));
// }

// /**
// * Checks if the given stack of integers is a palindrome.
// * Does not modify the original stack.
// *
// * @param stack The stack to check.
// * @return True if the stack is a palindrome, false otherwise.
// */
// public static boolean IsPalindrome(Stack<Integer> stack) {
// Stack<Integer> temp1 = new Stack<Integer>();
// Stack<Integer> copy = new Stack<Integer>();
// // Copy elements from stack to copy
// for (Integer item : stack) {
// copy.push(item);
// }

// while (!copy.isEmpty()) {
// temp1.push(copy.pop());
// }

// // Compare reversed stack with original
// return temp1.equals(stack);
// }

// /**
// * Placeholder for alternative palindrome check.
// * Currently always returns false.
// *
// * @param stack The stack to check.
// * @return False.
// */
// public static boolean IsPalindrome2(Stack<Integer> stack) {
// Stack<Integer> temp = new Stack<Integer>();
// for (Integer item : stack)
// temp.push(item);

// return false;
// }

// /**
// * Extracts only bracket characters from the input string.
// *
// * @param array The input string.
// * @return A string containing only brackets.
// */
// public static String OnlyBrackets(String array) {
// String brackets = "(){}[]";
// String onlyBrackets = "";
// for (char c : array.toCharArray()) {
// if (brackets.indexOf(c) != -1) {
// onlyBrackets += c;
// }
// }
// return onlyBrackets;
// }

// /**
// * Checks if the brackets in the input string are balanced and properly
// nested.
// *
// * @param array The input string.
// * @return True if brackets are balanced, false otherwise.
// */
// public static boolean CheckBrackets(String array) {
// String onlyBrackets = OnlyBrackets(array);

// Stack<Character> stack = new Stack<Character>();
// for (char c : onlyBrackets.toCharArray()) {
// if (c == '(' || c == '{' || c == '[') {
// stack.push(c);
// } else {
// if (stack.isEmpty()) {
// return false;
// }
// char top = stack.pop();
// if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top
// != '[')) {
// return false;
// }
// }
// }
// return stack.isEmpty();
// }
// }
