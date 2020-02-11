package strings;

public class AlternatingCharacters {

    static int alternatingCharacters(String s) {
        int delCount = 0;
        for (int i = 0; i < s.length() - 1; i ++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                delCount++;
            }
        }
        return delCount;
    }
}
