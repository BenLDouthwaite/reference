package adventofcode2021.day8;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//            String dummyString = input.get(0);
            String[] inputs = signalInputString.split("\\|");
            String[] signalPatterns = inputs[0].trim().split(" ");
            String[] fourDigitOutput = inputs[1].trim().split(" ");

            log.info(signalInputString);
            log.info("INP {}", inputs);
            log.info("SP {}", Arrays.asList(signalPatterns));
            log.info("FDO {}", Arrays.asList(fourDigitOutput));

            List<String> sortedSignalPatterns = Arrays.asList(signalPatterns).stream()
                    .sorted(Comparator.comparing(String::length)
                            .thenComparing(String::valueOf))
                    .collect(Collectors.toList());

            Map<Integer, List<String>> signalLengthMap = Arrays.asList(signalPatterns).stream()
                    .sorted(Comparator.comparing(String::length)
                            .thenComparing(String::valueOf))
                    .collect(Collectors.groupingBy(String::length));

            List<Set<Character>> numberedInputs = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                numberedInputs.add(new HashSet<>());
            }

            String one = sortedSignalPatterns.get(0); // 2 chars
            Set<Character> oneChars = charSet(one);
            numberedInputs.set(1, oneChars);


            String seven = sortedSignalPatterns.get(1); // 3 chars
            Set<Character> sevenChars = charSet(seven);
            numberedInputs.set(7, sevenChars);

            String four = sortedSignalPatterns.get(2); // 4 chars
            numberedInputs.set(4, charSet(four));


            String eight = sortedSignalPatterns.get(9); // 7 chars
            numberedInputs.set(8, charSet(eight));

            // union of 6 and 9 = 5. but could also be 0 nevermind
            // 6 = the only one that is 6 segments, but without _either_ of the segments from A.

            String zeroSixNineA = sortedSignalPatterns.get(6);
            String zeroSixNineB = sortedSignalPatterns.get(7);
            String zeroSixNineC = sortedSignalPatterns.get(8);

            String six = Stream.of(zeroSixNineA, zeroSixNineB, zeroSixNineC)
                    .filter(chars -> !charSet(chars).containsAll(oneChars))
                    .findFirst().get();
            numberedInputs.set(6, charSet(six));

            String nine = Stream.of(zeroSixNineA, zeroSixNineB, zeroSixNineC)
                    .filter(chars -> charSet(chars).containsAll(charSet(four)))
                    .findFirst().get();
            numberedInputs.set(9, charSet(nine));


            String zero = Stream.of(zeroSixNineA, zeroSixNineB, zeroSixNineC)
                    .filter(s -> !Set.of(six, nine).contains(s))
                    .findFirst().get();
            numberedInputs.set(0, charSet(zero));

            List<String> fiveCharStrings = signalLengthMap.get(5);

            Set<Character> fiveChars = new HashSet<>();
            fiveChars.addAll(charSet(nine));
            fiveChars.retainAll(charSet(six));

            String five = fiveCharStrings.stream()
                    .filter(pattern -> charSet(pattern).equals(fiveChars))
                    .findFirst().get();
            numberedInputs.set(5, charSet(five));


            String two = Stream.of(sortedSignalPatterns.get(3), sortedSignalPatterns.get(4), sortedSignalPatterns.get(5))
                    .filter(chars -> !five.equals(chars))
                    .filter(chars -> {
                        log.info("CHARS FOR 2, {}", chars);
                        Set<Character> characters = charSet(chars);
                        boolean b = !characters.containsAll(oneChars);
                        return b;
                    })
                    .findFirst().get();
//        numberedInputs[2] = two;
            numberedInputs.set(2, charSet(two));

            String three = Stream.of(sortedSignalPatterns.get(3), sortedSignalPatterns.get(4), sortedSignalPatterns.get(5))
                    .filter(chars -> !five.equals(chars))
                    .filter(chars -> !two.equals(chars))
                    .findFirst().get();
//        numberedInputs[3] = three;
            numberedInputs.set(3, charSet(three));

            // 6 and 9 the same except 2
            log.info("ZERO :" + zero);
            log.info("ONE :" + one);
            log.info("TWO : " + two);
            log.info("THREE : " + three);
            log.info("FOUR :" + four);
            log.info("FIVE :" + five);
            log.info("SIX: " + six);
            log.info("SEVEN :" + seven);
            log.info("EIGHT: " + eight);
            log.info("NINE: " + nine);

            // TODO Pull charSets into an array for each number
            // a = 7 - 4.
            Set<Character> aValSet = sub(charSet(seven), charSet(four));
            char aVal = onlyChar(aValSet);
            log.info("A Val: " + aVal);

            // b = 4 - 3
            char bVal = onlyChar(sub(charSet(four), charSet(three)));
            log.info("B Val: " + bVal);

            // c = 9 - 5.
            char cVal = onlyChar(sub(charSet(nine), charSet(five)));
            log.info("C Val: " + cVal);

            // d = 8c - 0c.
            char dVal = onlyChar(sub(charSet(eight), charSet(zero)));
            log.info("D Val: " + dVal);

            // e = 8 - 9
            char eVal = onlyChar(sub(charSet(eight), charSet(nine)));
            log.info("E Val: " + eVal);

            // f = 8 minus the rest.
            char fVal = onlyChar(sub(charSet(one), charSet(two)));
            log.info("F Val: " + fVal);

            // g = 9c - 4c - 7c.
            char gVal = onlyChar(sub(charSet(nine), charSet(four), charSet(seven)));
            log.info("G Val: " + gVal);

            log.info("SSP: " + sortedSignalPatterns);

            Map<Set<Character>, Integer> patternToValue = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                patternToValue.put(numberedInputs.get(i), i);
            }


            String collect = Arrays.stream(fourDigitOutput).map(fdo -> {
                        Set<Character> fdoCharSet = charSet(fdo);
                        Integer integer = patternToValue.get(fdoCharSet);
                        log.info("FDO {}, INT : {}", fdo, integer);
                        return integer;
                    })
                    .map(val -> String.valueOf(val))
                    .collect(Collectors.joining());

            Integer intCollect = Integer.valueOf(collect);

            total += intCollect;

            log.info("Four Digit Output: {} - {}", fourDigitOutput, intCollect);
        }

        return total;
    }

    private static char onlyChar(Set<Character> aValSet) {
        if (aValSet.size() != 1) {
            throw new IllegalArgumentException("Set must contain only 1 item. Logic error: " + aValSet);
        }
        return aValSet.stream().findFirst().get();
    }

    private static Set<Character> sub(Set<Character> startingValues, Set<Character>... charSets) {

        // 9361 failing.
        log.info("STARTING VALUES : {}, SUBTRACTING : {}", startingValues, charSets);
        Set<Character> resultSet = new HashSet<>(startingValues);
        Arrays.stream(charSets).forEach(resultSet::removeAll);
        return resultSet;
    }

    private static Set<Character> charSet(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
    }

    public static int digitCount(List<String> input) {
        return (int)input.stream()
                .map(entry -> entry.split("\\|")[1])
                .flatMap(outputs -> Arrays.stream(outputs.trim().split(" ")))
                // TODO Could extract a map of input 'numbers' to their segment count?
                .filter(output -> Set.of(2, 4, 3, 7).contains(output.length()))
                .count();
    }
}
