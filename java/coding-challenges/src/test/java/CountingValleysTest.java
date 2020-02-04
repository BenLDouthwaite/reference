import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CountingValleysTest {

    @Test
    public void countingValleys_emptyInput() {
        String path = "";
        int expectedValleyCount = 0;

        int valleysCount = CountingValleys.countingValleys(path.length(), path);
        assertThat(valleysCount, is(expectedValleyCount));
    }

    @Test(expected = IllegalArgumentException.class)
    public void countingValleys_nullInput() {
        String path = null;

        CountingValleys.countingValleys(1, path); // shouldn't happen, but let's guard anyway.
    }

    @Test
    public void countingValleys_singleValleyInput() {
        String path = "DU";
        int expectedValleyCount = 1;

        int valleysCount = CountingValleys.countingValleys(path.length(), path);
        assertThat(valleysCount, is(expectedValleyCount));
    }

    @Test
    public void countingValleys_singleMountainInput() {
        String path = "UD";
        int expectedValleyCount = 0;

        int valleysCount = CountingValleys.countingValleys(path.length(), path);
        assertThat(valleysCount, is(expectedValleyCount));
    }

    @Test
    public void countingValleys_twoValleysInput() {
        String path = "DUDU";
        int expectedValleyCount = 2;

        int valleysCount = CountingValleys.countingValleys(path.length(), path);
        assertThat(valleysCount, is(expectedValleyCount));
    }

    @Test
    public void countingValleys_problemDefinitionInput() {
        String path = "UDDDUDUU";
        int expectedValleyCount = 1;

        int valleysCount = CountingValleys.countingValleys(path.length(), path);
        assertThat(valleysCount, is(expectedValleyCount));
    }

    @Test
    public void countingValleys_manyValleys() {
        String path = "DUDUDUDUDUDUDUDUDUDU"; // 10 down and up.
        int expectedValleyCount = 10;

        int valleysCount = CountingValleys.countingValleys(path.length(), path);
        assertThat(valleysCount, is(expectedValleyCount));
    }

    @Test
    public void countingValleys_manyMountains() {
        String path = "UDUDUDUDUDUDUDUDUDUD"; // 10 up and down.
        int expectedValleyCount = 0;

        int valleysCount = CountingValleys.countingValleys(path.length(), path);
        assertThat(valleysCount, is(expectedValleyCount));
    }

    @Test(expected = IllegalArgumentException.class)
    public void countringValleys_invalidInput() {
        String path = "AB";

        CountingValleys.countingValleys(path.length(), path);
    }
}