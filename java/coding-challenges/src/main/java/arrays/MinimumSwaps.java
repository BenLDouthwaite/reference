package arrays;

public class MinimumSwaps {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {

//        // CAN SWAP ANY TWO ELEMENTS
        int swapCount = 0;

//        while (arrayIsOrdered(arr)){
//
//        }
        // 2. Find any swaps that would resolve 2 at the same time.
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == i + 1){
                continue;
            }

            int currentValue = arr[i];
            int currentValueCorrectIndex = currentValue - 1;

            int temp = arr[i];
            arr[i] = arr[currentValueCorrectIndex];
            arr[currentValueCorrectIndex] = temp;
            swapCount++;
        }



        // 2. Find any swaps that would resolve 2 at the same time.
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == i + 1){
                continue;
            }

            int currentValue = arr[i];
            int currentValueCorrectIndex = currentValue - 1;

            int temp = arr[i];
            arr[i] = arr[currentValueCorrectIndex];
            arr[currentValueCorrectIndex] = temp;
            swapCount++;
        }

        return swapCount;
    }

    private static boolean arrayIsOrdered(int[] arr) {
        for (int i = 0; i < arr.length; i ++){
            if (arr[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

}
