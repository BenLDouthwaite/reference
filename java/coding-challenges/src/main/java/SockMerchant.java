import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


public class SockMerchant {

    /**
     * Improved implementation based on discussion on Hackerrank. Single pass over the list, doesn't increase memory. much better
     */
    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {

        Set<Integer> sockColours = new HashSet<>();
        int pairsOfMatchingSocks = 0;
        for (int i = 0; i < n; i ++) {
            if (sockColours.remove(ar[i])) {
                pairsOfMatchingSocks++;
            } else {
                sockColours.add(ar[i]);
            }
        }
        return pairsOfMatchingSocks;
    }

    /**
     * My original implementation, Relatively easy to ready, but iterates the list several times
     */
    static int sockMerchant_Original(int n, int[] ar) {

        Map<Integer, Long> sockColourCountMap = Arrays.stream(ar)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return sockColourCountMap.values()
                .stream()
                .mapToInt(Long::intValue)
                .map(integer -> integer / 2) // Ignore remainder with integer division
                .sum();
    }
}
