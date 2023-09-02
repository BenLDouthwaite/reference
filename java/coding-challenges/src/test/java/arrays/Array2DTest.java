package arrays;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Array2DTest {

    @Test
    public void hourglassSum_problemDefinition() {
        int[][] input = {
                {1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 2, 4, 4, 0},
                {0, 0, 0, 2, 0, 0},
                {0, 0, 1, 2, 4, 0},
        };

        int expectedOutput = 19;

        int output = Array2D.hourglassSum(input);
        assertThat(output, is(expectedOutput));
    }

    @Test
    public void hourglassSum_SingleHourglass_all_1() {
        int[][] input = {
                {1, 1, 1},
                {0, 1, 0},
                {1, 1, 1},
        };

        int expectedOutput = 7;

        int output = Array2D.hourglassSum(input);
        assertThat(output, is(expectedOutput));
    }

    @Test
    public void hourglassSum_SingleHourglass_all_9() {
        int[][] input = {
                {9, 9, 9},
                {0, 9, 0},
                {9, 9, 9},
        };

        int expectedOutput = 63;

        int output = Array2D.hourglassSum(input);
        assertThat(output, is(expectedOutput));
    }

    @Test
    public void hourglassSum_FourHourglasses_top_left() {
        int[][] input = {
                {9, 9, 9, 1},
                {0, 9, 0, 1},
                {9, 9, 9, 1},
                {1, 1, 1, 1}
        };

        int expectedOutput = 63;

        int output = Array2D.hourglassSum(input);
        assertThat(output, is(expectedOutput));
    }

    @Test
    public void hourglassSum_FourHourglasses_bottom_right() {
        int[][] input = {
                {0, 0, 0, 0},
                {0, 1, 2, 3},
                {0, 0, 4, 0},
                {0, 5, 6, 7}
        };

        int expectedOutput = 28;

        int output = Array2D.hourglassSum(input);
        assertThat(output, is(expectedOutput));
    }

    @Test
    public void hourglassSum_FourHourglasses_negatives() {
        int[][] input = {
                {-1, -1, 0, -9, -2, -2},
                {-2, -1, -6, -8, -2, -5},
                {-1, -1, -1, -2, -3, -4},
                {-1, -9, -2, -4, -4, -5},
                {-7, -3, -3, -2, -9, -9},
                {-1, -3, -1, -2, -4, -5},
        };

        int expectedOutput = -6;

        int output = Array2D.hourglassSum(input);
        assertThat(output, is(expectedOutput));
    }
}