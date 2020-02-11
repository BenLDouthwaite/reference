package arrays;

import java.util.Arrays;

public class ArrayManipulation {

    // Utilise 'difference array'
    static long arrayManipulation(int n, int[][] queries) {
        long[] diffArr = new long[n + 1];
        for (int[] query: queries) {
            int start = query[0];
            int end = query[1];
            long change = query[2];
            diffArr[start] += change;
            if (end + 1 <= n) {
                diffArr[end + 1] -= change;
            }
        }
        long sum = 0;
        long max = 0;
        for (int i = 1; i <= n; i++) {
            sum += diffArr[i];
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
