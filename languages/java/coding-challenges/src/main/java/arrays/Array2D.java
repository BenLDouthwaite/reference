package arrays;

public class Array2D {
    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        Integer largestHourglassValue = null;
        for (int ri = 0; ri < arr.length - 2; ri++) {
            for (int ci = 0; ci < arr.length - 2; ci++) {
                int hourglassValue = hourglass(arr, ri, ci);
                if (largestHourglassValue == null || hourglassValue > largestHourglassValue) {
                    largestHourglassValue = hourglassValue;
                }
            }
        }
        return largestHourglassValue;
    }

    private static int hourglass(int[][] a, int ri, int ci) {
        return a[ri][ci] + a[ri][ci+1] + a[ri][ci+2]
                        + a[ri+1][ci +1]
                + a[ri +2][ci] + a[ri + 2][ci + 1] + a[ri + 2][ci + 2];
    }

}
