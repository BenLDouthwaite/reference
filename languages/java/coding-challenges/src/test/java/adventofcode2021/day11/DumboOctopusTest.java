package adventofcode2021.day11;

import adventofcode2021.day10.SyntaxScoring;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class DumboOctopusTest {

    @Test
    public void flashingLights_cascasingFlashes() {

        // given
        List<String> input = List.of(
                "11111",
                "19991",
                "19191",
                "19991",
                "11111"
        );

        // when
        int result = DumboOctopus.flashingLights(input, Optional.of(2));

        // then
        assertThat(result).isEqualTo(9);
    }

    @Test
    public void flashingLights() {

        // given
        List<String> input = List.of(
                "5483143223",
                "2745854711",
                "5264556173",
                "6141336146",
                "6357385478",
                "4167524645",
                "2176841721",
                "6882881134",
                "4846848554",
                "5283751526"
        );

        // when
        int result = DumboOctopus.flashingLights(input, Optional.of(100));

        // then
        assertThat(result).isEqualTo(1656);
    }
}