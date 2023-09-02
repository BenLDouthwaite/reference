package adventofcode2021.day6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class LanternfishParameterisedTest {

    private String input;
    private int days;
    private long result;

    public LanternfishParameterisedTest(String input, int days, long result) {
        this.input = input;
        this.days = days;
        this.result = result;
    }

    @Parameterized.Parameters()
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {"3", 1, 1L},
                {"3", 4, 2L},
                {"3", 10, 2L},
                {"5", 10, 2L},
                {"3", 11, 3L},
                {"3", 12, 3L},
                {"3", 13, 4L},
                {"3", 17, 4L},
                {"3", 18, 5L},
                {"0,2", 10, 7L},
                {"3", 20, 7L},
                {"3", 22, 8L},
                {"3", 30, 15L},
                {"1, 3, 3, 5, 3, 5, 5, 0, 3, 5, 5, 5, 7, 7, 7", 10, 33L},
                {"3", 40, 33L},
                {"3", 50, 86L},
                {"3", 60, 193L},
                {"3", 70, 485L},
                {"3", 80, 1154L},
                {"4", 80, 1034L},
                {"5", 80, 950L},
                {"3,4,3,1,2", 1, 5L},
                {"3,4,3,1,2", 2, 6L},
                {"3,4,3,1,2", 3, 7L},
                {"3,4,3,1,2", 4, 9L},
                {"3,4,3,1,2", 18, 26L},
                {"3,4,3,1,2", 80, 5934L},
                {"3", 150, 505597L},
                {"3,4,3,1,2", 256, 26984457539L},
        });
    }

    @Test
    public void lanternfish() {
        assertThat(Lanternfish.lanternfish(input, days)).isEqualTo(result);
    }

}