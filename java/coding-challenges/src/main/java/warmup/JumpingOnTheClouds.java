package warmup;

import java.util.Currency;

public class JumpingOnTheClouds {

    private static final int CUMULUS = 0;
    private static final int THUNDERHEAD = 1;

    // Complete the jumpingOnClouds function below.
    public static int jumpingOnClouds(int[] c) {

        int jumpCounter = 0;
        for (int i = 0; i < c.length - 1; i++, jumpCounter++) {
            if (i < c.length - 2 && c[i+2] == CUMULUS) {
                i++; // Don't check jumped cloud
            }
        }
        return jumpCounter;
    }

    // Complete the jumpingOnClouds function below.
    public static int jumpingOnClouds_initialImplementation(int[] c) {

        int jumpCounter = 0;
        for (int i = 0; i < c.length - 1; i++) {
            if (c[i+1] == THUNDERHEAD) {
                i++; // Don't check jumped cloud
            } else
            if (i < c.length - 2
                    && c[i+1] == CUMULUS
                    && c[i+2] == CUMULUS) {
                i++; // Don't check jumped cloud
            }
            jumpCounter++;
        }
        return jumpCounter;
    }
}
