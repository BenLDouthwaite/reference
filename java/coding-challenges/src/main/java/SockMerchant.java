import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class SockMerchant {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {

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
