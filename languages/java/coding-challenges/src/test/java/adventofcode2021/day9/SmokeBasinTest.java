package adventofcode2021.day9;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SmokeBasinTest {

    @Test
    public void lowPointRiskLevel() {
        // given
        List<String> input = List.of(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
        );

        // when
        int result = SmokeBasin.lowPointRiskLevel(input);

        // then
        assertThat(result).isEqualTo(15);
    }
}