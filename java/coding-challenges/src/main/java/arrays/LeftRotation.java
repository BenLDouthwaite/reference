package arrays;

public class LeftRotation {

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {

        // calculate number of passes.
        int numberOfPasses = d % a.length;
        if (numberOfPasses == 0) {
            return a;
        }

        int[] temp = new int[numberOfPasses];
        for (int i = 0; i < numberOfPasses; i++) {
            temp[i] = a[i];
        }

        for (int i = 0; i < a.length - numberOfPasses; i++) {
            a[i] = a[i + numberOfPasses];
        }

        int offset = a.length - numberOfPasses;
        for (int i = 0; i < numberOfPasses; i++) {
            a[i + offset] = temp[i];
        }

        return a;
    }
}
