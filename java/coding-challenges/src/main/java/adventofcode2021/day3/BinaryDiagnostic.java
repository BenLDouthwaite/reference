package adventofcode2021.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class BinaryDiagnostic {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day3/puzzleInput.txt");
        List<String> commands = Files.readAllLines(puzzleInputPath);

        int result1 = binaryDiagnostic(commands);
        System.out.println("Part 1: Result for puzzle input: " + result1);

        int result2 = binaryDiagnosticLifeSupport(commands);
        System.out.println("Part 2: Result for puzzle input: " + result2);
    }


    public static int binaryDiagnosticLifeSupport(List<String> binNums) {

        String oxygen = getRating(binNums, true);
        String co2 = getRating(binNums, false);

        int oxygenDecimal = Integer.parseInt(oxygen, 2);
        int co2Decimal = Integer.parseInt(co2, 2);

        return oxygenDecimal * co2Decimal;
    }

    private static String getRating(List<String> binNums, boolean mostFrequentSearch) {

        List<String> candidateBinNums = new ArrayList<>(binNums);

        int digitLength = binNums.get(0).length();
        for (int i = 0; i < digitLength; i++) {
            if (candidateBinNums.size() == 1) {
                break;
            }
            int finalI = i;
            Map<Character, List<String>> charCount = candidateBinNums.stream()
                    .collect(Collectors.groupingBy(
                            binVal -> binVal.charAt(finalI)
                    ));
            List<String> zeroCharMatch = charCount.getOrDefault('0', Collections.emptyList());
            List<String> oneCharMatch = charCount.getOrDefault('1', Collections.emptyList());
            if (zeroCharMatch.size() == oneCharMatch.size()) {
                if (mostFrequentSearch) {
                    candidateBinNums = oneCharMatch;
                } else {
                    candidateBinNums = zeroCharMatch;
                }
                continue;
            }
            if ((zeroCharMatch.size() > oneCharMatch.size() && mostFrequentSearch) ||
                    (zeroCharMatch.size() < oneCharMatch.size() && !mostFrequentSearch)) {
                candidateBinNums = zeroCharMatch;
            } else {
                candidateBinNums = oneCharMatch;
            }
        }

        return candidateBinNums.get(0);
    }

    public static int binaryDiagnostic(List<String> binNums) {

        int binNumSize = binNums.get(0).length();
        Map<Integer, Integer> positionValues = new HashMap<>();
        for (String binNum : binNums) {
            char[] binVals = binNum.toCharArray();
            for (int i = 0; i < binVals.length; i++) {
                Integer positionValue = positionValues.getOrDefault(i, 0);
                if (binVals[i] == '0') {
                    positionValues.put(i, positionValue - 1);
                } else {
                    positionValues.put(i, positionValue + 1);
                }
            }
        }

        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int i = 0; i < binNumSize; i++) {
            Integer positionValue = positionValues.get(i);
            if (positionValue > 0) {// Most common bit = '1'
                gamma.append('1');
                epsilon.append('0');
            } else {
                gamma.append('0');
                epsilon.append('1');
            }
        }

        int gammaDecimal = Integer.parseInt(gamma.toString(), 2);
        int epsilonDecimal = Integer.parseInt(epsilon.toString(), 2);

        return gammaDecimal * epsilonDecimal;
    }
}
