public class CountingValleys {

    private static final char DOWNHILL = 'D';
    private static final char UPHILL = 'U';
    private static final int SEA_LEVEL = 0;

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {

        if (null == s) {
            throw new IllegalArgumentException("Path cannot be null");
        }

        int altitude = SEA_LEVEL;
        int valleysCount = 0;

        for (char step: s.toCharArray()) {
            if (step == DOWNHILL) {
                altitude--;
            } else if (step == UPHILL) {
                if (++altitude == SEA_LEVEL) {
                    valleysCount++;
                }
            } else {
                throw new IllegalArgumentException("All characters in the path must be 'U' or 'D'");
            }
        }
        return valleysCount;
    }
}
