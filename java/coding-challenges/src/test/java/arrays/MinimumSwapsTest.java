package arrays;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MinimumSwapsTest {

    @Test
    public void minimumSwaps_pd0() {
        int[] input = {4,3,1,2};

        int expectedResult = 3;

        int result = MinimumSwaps.minimumSwaps(input);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void minimumSwaps_pd1() {
        int[] input = {2,3,4,1,5};

        int expectedResult = 3;

        int result = MinimumSwaps.minimumSwaps(input);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void minimumSwaps_pd2() {
        int[] input = {1,3,5,2,4,6,7};

        int expectedResult = 3;

        int result = MinimumSwaps.minimumSwaps(input);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void minimumSwaps_noSwaps() {
        int[] input = {1,2};

        int expectedResult = 0;

        int result = MinimumSwaps.minimumSwaps(input);
        assertThat(result, is(expectedResult));
    }


    @Test
    public void minimumSwaps_singleSwap() {
        int[] input = {2,1};

        int expectedResult = 1;

        int result = MinimumSwaps.minimumSwaps(input);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void minimumSwaps_twoDouble_oneSingle_twoDouble() {
        int[] input = {1,3,2,6,7,8,5,4,9};

        int expectedResult = 4;

        int result = MinimumSwaps.minimumSwaps(input);
        assertThat(result, is(expectedResult));
    }

}