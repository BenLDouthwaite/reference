package adventofcode2021.day5;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HydrothermalVentureTest {

    @Test
    public void overlappingPoints_pt2() {
        // given
        List<String> input = List.of(
                "0,9 -> 5,9",
                "8,0 -> 0,8",
                "9,4 -> 3,4",
                "2,2 -> 2,1",
                "7,0 -> 7,4",
                "6,4 -> 2,0",
                "0,9 -> 2,9",
                "3,4 -> 1,4",
                "0,0 -> 8,8",
                "5,5 -> 8,2"
        );

        // when
        int result = HydrothermalVenture.overlappingPoints(input, true);

        // then
        assertThat(result).isEqualTo(12);
    }

    @Test
    public void overlappingPoints_pt1() {
        // given
        List<String> input = List.of(
                "0,9 -> 5,9",
                "8,0 -> 0,8",
                "9,4 -> 3,4",
                "2,2 -> 2,1",
                "7,0 -> 7,4",
                "6,4 -> 2,0",
                "0,9 -> 2,9",
                "3,4 -> 1,4",
                "0,0 -> 8,8",
                "5,5 -> 8,2"
        );

        // when
        int result = HydrothermalVenture.overlappingPoints(input, false);

        // then
        assertThat(result).isEqualTo(5);
    }
}