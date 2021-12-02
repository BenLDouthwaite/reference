package adventofcode2021.day2;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DiveTest {

    @Test
    public void diveAim_puzzleExample() {
        // given
        List<String> commands = List.of(
                "forward 5",
                "down 5",
                "forward 8",
                "up 3",
                "down 8",
                "forward 2"
        );

        // when
        int result = Dive.diveAim(commands);

        // then
        assertThat(result).isEqualTo(900);
    }

    @Test
    public void dive_puzzleExample() {
        // given
        List<String> commands = List.of(
                "forward 5",
                "down 5",
                "forward 8",
                "up 3",
                "down 8",
                "forward 2"
        );

        // when
        int result = Dive.dive(commands);

        // then
        assertThat(result).isEqualTo(150);
    }
}