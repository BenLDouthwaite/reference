package arrays;

public class LeftRotation {

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {

        // calculate number of passes.
        if (d % a.length == 0) {
            return a;
        }

        int numberOfPasses = d % a.length;

        // TODO Add temp array to store values, then reduce to 1 with better logic

//        int[] temp = new int[numberOfPasses];

        // Naive first pass, passes initial test case. not performant
        for (int j = 0; j < numberOfPasses; j ++) {
            int temp = a[0];
            for (int i = 0; i < a.length - 1; i++) {
                a[i] = a[i + 1];
            }
            a[a.length - 1] = temp;
        }
        return a;
    }
}
