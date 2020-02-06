package sorting;

public class BubbleSort {

    // Complete the countSwaps function below.
    static void countSwaps(int[] a) {

        int n = a.length;
        int swapCounter = 0;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
//                    swap(a[j], a[j + 1]);
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    swapCounter++;
                }
            }
        }

        System.out.println("Array is sorted in " + swapCounter + " swaps.");
        System.out.println("First element: " + a[0]);
        System.out.println("Last element: " + a[n-1]);
    }

    private static void swap(int i, int i1) {

    }


}
