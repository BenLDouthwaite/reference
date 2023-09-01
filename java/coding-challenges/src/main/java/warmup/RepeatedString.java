package warmup;

public class RepeatedString {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("s cannot be empty");
        }
        long factor = n / s.length();
        long factorResult = getCharacterCount(s, 'a') * factor;

        long rem = n % s.length();
        long remResult = getCharacterCount(s.substring(0, (int)rem), 'a');
        return factorResult + remResult;
    }

    private static long getCharacterCount(String s, char characterToCount) {
        return s.chars().filter(ch -> ch == characterToCount).count();
    }
}
