package stacksandqueues;

import java.util.Map;
import java.util.Stack;

public class BalancedBrackets {

    private static final Map<Character, Character> BRACKET_PAIRS = Map.of(
            '(', ')',
            '[', ']',
            '{', '}'
    );

    public static String isBalanced(String s) {

        int length = s.length();
        if (length % 2 != 0) {
            return "NO";
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i ++) {
            char bracket = s.charAt(i);
            if (BRACKET_PAIRS.containsKey(bracket)) {
                stack.push(bracket);
            } else { // Assume valid input, is a closing bracket
                if (stack.empty() || BRACKET_PAIRS.get(stack.pop()) != bracket) {
                    return "NO";
                }
            }
        }

        return stack.empty() ? "YES" : "NO";
    }

}
