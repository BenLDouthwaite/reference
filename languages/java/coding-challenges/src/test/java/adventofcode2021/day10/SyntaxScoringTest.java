package adventofcode2021.day10;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SyntaxScoringTest {

    @Test
    public void syntaxFixer() {

        // given
        List<String> input = List.of(
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
        );

        // when
        long result = SyntaxScoring.syntaxFixer(input);

        // then
        assertThat(result).isEqualTo(288957);
    }

    @Test
    public void syntaxFixer_singleinput_288957() {

        // given
        List<String> input = List.of(
                "[({(<(())[]>[[{[]{<()<>>" // - Complete by adding }}]])})].
        );

        // when
        long result = SyntaxScoring.syntaxFixer(input);

        // then
        assertThat(result).isEqualTo(288957);
    }

    @Test
    public void syntaxFixer_singleinput() {

        // given
        List<String> input = List.of(
            "<{([{{}}[<[[[<>{}]]]>[]]" // - Complete by adding ])}>.
        );

        // when
        long result = SyntaxScoring.syntaxFixer(input);

        // then
        assertThat(result).isEqualTo(294);
    }

    @Test
    public void syntaxScore_singleinput_ExpectedSquareFoundCurly() {

        // given
        List<String> input = List.of(
                "{([(<{}[<>[]}>{[]{[(<()>"
        );

        // when
        int result = SyntaxScoring.syntaxScore(input);

        // then
        assertThat(result).isEqualTo(1197);
    }

    @Test
    public void syntaxScore() {

        // given
        List<String> input = List.of(
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
        );

        // when
        int result = SyntaxScoring.syntaxScore(input);

        // then
        assertThat(result).isEqualTo(26397);
    }
}