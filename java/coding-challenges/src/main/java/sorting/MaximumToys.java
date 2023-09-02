package sorting;

import java.util.Arrays;

public class MaximumToys {

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {

        Arrays.sort(prices);
        int numberOfToys = 0;
        int spent = 0;
        for (int i = 0; i < prices.length ; i++) {
            if (spent + prices[i] < k) {
                numberOfToys ++;
                spent += prices[i];
            } else {
                break;
            }
        }

        return numberOfToys;
    }

}
