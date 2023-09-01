package adventofcode2021.day10;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class SyntaxScoring {

    private static final Map<Character, Character> BRACKET_PAIRS = Map.of(
            '(', ')',
            '[', ']',
            '{', '}',
            '<', '>'
    );

    private static final Map<Character, Integer> CORRUPTED_BRACKET_POINTS = Map.of(
            ')', 3,
            ']', 57,
            '}', 1197,
            '>', 25137
    );

    private static final Map<Character, Integer> FIX_BRACKET_POINTS = Map.of(
            ')', 1,
            ']', 2,
            '}', 3,
            '>', 4
    );

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day10/puzzleInput.txt");
        List<String> input = Files.readAllLines(puzzleInputPath);

        int result1 = syntaxScore(input);
        log.info("Part 1: Result for puzzle input: " + result1);

        long result2 = syntaxFixer(input);
        log.info("Part 2: Result for puzzle input: " + result2);
    }

    public static long syntaxFixer(List<String> input) {

        List<Long> scores = input.stream()
                .map(SyntaxScoring::getCharactersToFixString)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(SyntaxScoring::calculateCompletionStringScore)
                .sorted()
                .collect(Collectors.toList());

        // Return middle item.
        int size = scores.size();
        return scores.get(size / 2);
    }

    // Empty optional means the string is corrupted
    private static Optional<String> getCharactersToFixString(String brackets) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < brackets.length(); i ++) {
            char bracket = brackets.charAt(i);
            if (BRACKET_PAIRS.containsKey(bracket)) {
                stack.push(bracket);
            } else { // Assume valid input, is a closing bracket
                Character poppedCharacter = stack.pop();
                if (stack.isEmpty() || BRACKET_PAIRS.get(poppedCharacter) != bracket) {
                    return Optional.empty();
                }
            }
        }

        String result = stack.stream()
                .map(BRACKET_PAIRS::get)
                .map(String::valueOf)
                .collect(Collectors.joining());
        return Optional.of(result);
    }

    private static long calculateCompletionStringScore(String fix) {
        long fixScore = 0;
        for (int i = 0; i < fix.length(); i ++) {
            char bracket = fix.charAt(i);
            fixScore *= 5;
            Integer points = FIX_BRACKET_POINTS.get(bracket);
            fixScore += points;
        }
        return fixScore;
    }

    public static int syntaxScore(List<String> input) {

        Stack<Character> stack = new Stack<>();

        int corruptionPoints = 0;

        for (String brackets : input) {
            for (int i = 0; i < brackets.length(); i ++) {
                char bracket = brackets.charAt(i);
                if (BRACKET_PAIRS.containsKey(bracket)) {
                    stack.push(bracket);
                } else { // Assume valid input, is a closing bracket
                    Character poppedCharacter = stack.pop();
                    if (stack.empty() || BRACKET_PAIRS.get(poppedCharacter) != bracket) {
                        Integer integer = CORRUPTED_BRACKET_POINTS.get(bracket);
                        corruptionPoints += integer;
                    }
                }
            }
        }

        return corruptionPoints;
    }
}
