package adventofcode2021.day3;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BinaryDiagnosticTest {

    // 4375225

    @Test
    public void binaryDiagnosticLifeSupport_puzzleExample() {
        // given
        List<String> binNums = List.of(
                "00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010"
        );

        // when
        int result = BinaryDiagnostic.binaryDiagnosticLifeSupport(binNums);

        // then
        assertThat(result).isEqualTo(230);
    }

    @Test
    public void binaryDiagnostic_puzzleExample() {
        // given
        List<String> binNums = List.of(
                "00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010"
        );

        // when
        int result = BinaryDiagnostic.binaryDiagnostic(binNums);

        // then
        assertThat(result).isEqualTo(198);
    }
}