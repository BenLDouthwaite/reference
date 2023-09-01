package ctci;

public class ArraysAndStrings {

    // 1.1 Is Unique
    public static boolean charsUnique(String s) {
        boolean[] charPresent = new boolean[128];
        for (char c : s.toCharArray()) {
            if (charPresent[c]) {
                return false;
            }
            charPresent[c] = true;
        }
        return true;
    }
}
