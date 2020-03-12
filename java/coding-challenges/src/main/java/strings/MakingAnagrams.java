package strings;

import java.util.*;
import java.util.stream.Collectors;

public class MakingAnagrams {

    static int makeAnagram(String a, String b) {
        int[] charCount = new int[26];
        a.chars().forEach(c -> charCount[c - 97]++);
        b.chars().forEach(c -> charCount[c - 97]--);
        return Arrays.stream(charCount).map(Math::abs).sum();
    }

    static int makeAnagram_originalImplementation(String a, String b) {

        if (a.equals(b)) {
            return 0;
        }

        Map<Character, Integer> aCharCount = getCharacterCountMap(a);
        Map<Character, Integer> bCharCount = getCharacterCountMap(b);

        Set<Character> charsUnion = new HashSet<>(aCharCount.keySet());
        charsUnion.addAll(bCharCount.keySet());

        bCharCount.finalize()

        int charaRemovalCount = 0;
        for(Character c: charsUnion) {
            int diff = Math.abs(aCharCount.getOrDefault(c, 0) - bCharCount.getOrDefault(c, 0));
            charaRemovalCount += diff;
        }

        return charaRemovalCount;
    }

    private static Map<Character, Integer> getCharacterCountMap(String s) {
        return s.chars().boxed()
                .collect(Collectors.toMap(k -> (char) k.intValue(), v -> 1, Integer::sum));
    }
}
