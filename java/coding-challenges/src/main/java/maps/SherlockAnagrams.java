package maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SherlockAnagrams {

    // Given a string, find the count of pairs of substring that are anagrams of each other.
    public static int sherlockAndAnagrams(String s) {

        List<String> substrings = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substring = s.substring(i, j + 1);
                char[] chars = substring.toCharArray();
                Arrays.sort(chars);
                substrings.add(new String(chars));
            }
        }

        long sum = substrings.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // instances of each 'set of chars' as a String.
                .values()
                .stream()
                .map(val -> (val - 1) * val / 2 ) // Triangle number formula. - offset by 1
                .mapToLong(val -> val)
                .sum();
        return (int)sum;
    }

}
