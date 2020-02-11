package arrays;

public class MinimumSwaps {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int swapCount = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == i + 1){
                continue;
            }
            int currentValueCorrectIndex = arr[i] - 1;
            int temp = arr[i];
            arr[i] = arr[currentValueCorrectIndex];
            arr[currentValueCorrectIndex] = temp;
            swapCount++;
            i--; // As we swapped the first index, we need to check it again.
        }
        return swapCount;
    }

    // Complete the minimumSwaps function below.
    static int minimumSwapsInitialImplementation(int[] arr) {
        int swapCount = 0;
        while (!arrayIsOrdered(arr)){
            for (int i = 0; i < arr.length; i++){

                // Implementation swapping current value to it's correct position.
                if (arr[i] == i + 1){
                    continue;
                }

                int currentValueCorrectIndex = arr[i] - 1;

                int temp = arr[i];
                arr[i] = arr[currentValueCorrectIndex];
                arr[currentValueCorrectIndex] = temp;
                swapCount++;
            }
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
