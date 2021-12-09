package adventofcode2021.day8;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class SevenSegmentSearch {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day8/puzzleInput.txt");
        List<String> input = Files.readAllLines(puzzleInputPath);

        int result1 = digitCount(input);
        log.info("Part 1: Result for puzzle input: " + result1);

        int result2 = digitValuesTotal(input);
        log.info("Part 2: Result for puzzle input: " + result2);
    }

    public static int digitValuesTotal(List<String> input) {

        int total = 0;

        for (String signalInputString: input) {
            String[] inputs = signalInputString.split("\\|");
            String[] signalPatterns = inputs[0].trim().split(" ");
            String[] fourDigitOutput = inputs[1].trim().split(" ");

            Map<Integer, List<String>> signalLengthMap = Arrays.asList(signalPatterns).stream()
                    .sorted(Comparator.comparing(String::length)
                            .thenComparing(String::valueOf))
                    .collect(Collectors.groupingBy(String::length));

            List<Set<Character>> numberedInputs = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                numberedInputs.add(new HashSet<>());
            }

            numberedInputs.set(1, charSet(onlyItem(signalLengthMap.get(2))));
            numberedInputs.set(4, charSet(onlyItem(signalLengthMap.get(4))));
            numberedInputs.set(7, charSet(onlyItem(signalLengthMap.get(3))));
            numberedInputs.set(8, charSet(onlyItem(signalLengthMap.get(7))));

            List<String> sixCharacterPatterns = signalLengthMap.get(6);

            String zero = sixCharacterPatterns.stream()
                    .filter(pattern -> charSet(pattern).containsAll(numberedInputs.get(1)))
                    .findFirst().get();
            numberedInputs.set(0, charSet(zero));

            String six = sixCharacterPatterns.stream()
                    .filter(pattern -> !charSet(pattern).containsAll(numberedInputs.get(1)))
                    .findFirst().get();
            numberedInputs.set(6, charSet(six));

            String nine = sixCharacterPatterns.stream()
                    .filter(pattern -> charSet(pattern).containsAll(numberedInputs.get(4)))
                    .findFirst().get();
            numberedInputs.set(9, charSet(nine));

            List<String> fiveCharacterPattern = signalLengthMap.get(5);

            String three = fiveCharacterPattern.stream()
                    .filter(pattern -> charSet(pattern).containsAll(numberedInputs.get(1)))
                    .findFirst().get();
            numberedInputs.set(3, charSet(three));

            Set<Character> fiveChars = new HashSet<>(charSet(nine));
            fiveChars.retainAll(charSet(six));
            String five = fiveCharacterPattern.stream()
                    .filter(pattern -> charSet(pattern).equals(fiveChars))
                    .findFirst().get();
            numberedInputs.set(5, charSet(five));

            String two = fiveCharacterPattern.stream()
                    .filter(pattern -> !five.equals(pattern))
                    .filter(pattern -> !charSet(pattern).containsAll(numberedInputs.get(1)))
                    .findFirst().get();
            numberedInputs.set(2, charSet(two));

            Map<Set<Character>, Integer> patternToValue = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                patternToValue.put(numberedInputs.get(i), i);
            }

            String fourDigitOutputResult = Arrays.stream(fourDigitOutput)
                    .map(fdo -> patternToValue.get(charSet(fdo)))
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            total += Integer.parseInt(fourDigitOutputResult);
        }

        return total;
    }

    private static <E> E onlyItem(Collection<E> aValSet) {
        if (aValSet.size() != 1) {
            throw new IllegalArgumentException("Set must contain only 1 item. Logic error: " + aValSet);
        }
        return aValSet.stream().findFirst().get();
    }

    private static Set<Character> charSet(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
    }

    // Part 1
    public static int digitCount(List<String> input) {
        return (int)input.stream()
                .map(entry -> entry.split("\\|")[1])
                .flatMap(outputs -> Arrays.stream(outputs.trim().split(" ")))
                .filter(output -> Set.of(2, 4, 3, 7).contains(output.length()))
                .count();
    }
}
