package adventofcode2021.day12;

import adventofcode2021.day10.SyntaxScoring;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class PassagePathingTest {

    @Test
    public void pathCount() {
        // given
        List<String> input = List.of(
                "start-A",
                "start-b",
                "A-c",
                "A-b",
                "b-d",
                "A-end",
                "b-end"
        );

        // when
        long result = PassagePathing.pathCount(input);

        // then
        assertThat(result).isEqualTo(10);
    }
}