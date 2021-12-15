package adventofcode2021.day11;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Slf4j
public class DumboOctopus {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day11/puzzleInput.txt");
        List<String> input = Files.readAllLines(puzzleInputPath);

        int result1 = flashingLights(input, Optional.of(100));
        log.info("Part 1: Result for puzzle input: " + result1);

        long result2 = flashingLights(input, Optional.empty());
        log.info("Part 2: Result for puzzle input: " + result2);
    }

    public static int flashingLights(List<String> input, Optional<Integer> stepsOpt) {

        int steps = stepsOpt.isPresent() ? stepsOpt.get() : 1000;

        int totalFlashes = 0;
        int[][] energyMap = new int[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            int[] inputNums = Arrays.stream(input.get(i).split(""))
                    .mapToInt(Integer::valueOf)
                    .toArray();
            energyMap[i] = inputNums;
        }

        printEnergyMap(energyMap);

        for (int step = 1; step <= steps; step++) {

            int rows = input.size();
            int columns = input.get(0).length();

            int[][] flashedMap = new int[input.size()][input.get(0).length()];

            for (int i = 0; i < rows; i ++) {
                for (int j = 0; j < columns; j++) {
                    energyMap[i][j]++;
                }
            }

            for (int i = 0; i < rows; i ++) {
                for (int j = 0; j < columns; j++) {
                    if (energyMap[i][j] > 9) {
                        flash(energyMap, i, j, flashedMap);
                    }
                }
            }

            for (int i = 0; i < rows; i ++) {
                for (int j = 0; j < columns; j++) {
                    if (flashedMap[i][j] == 1) {
                        energyMap[i][j] = 0;
                    }
                }
            }

            long numberOfFlashes = Arrays.stream(flashedMap)
                    .flatMapToInt(IntStream::of)
                    .filter(v -> v == 1)
                    .count();
            totalFlashes += numberOfFlashes;

            if (stepsOpt.isEmpty() && numberOfFlashes == ((long) rows * columns)) { // Only check in part 2. Could be neater but whatever
                log.info("First simultaneous flash = step: {}", step);
                return step;
            }
        }

        return totalFlashes;
    }

    private static void flash(int[][] energyMap, int i, int j, int[][] flashedMap) {

        if (flashedMap[i][j] == 1) {
            return; // Already flashed this step
        }

        int rowLen = energyMap.length;
        int colLen = energyMap[0].length;

        energyMap[i][j]++;
        flashedMap[i][j] = 1;

        // Rotate clockwise from North.
        condFlash(energyMap, i - 1, j, flashedMap, rowLen, colLen);
        condFlash(energyMap, i - 1, j + 1, flashedMap, rowLen, colLen);
        condFlash(energyMap, i, j + 1, flashedMap, rowLen, colLen);
        condFlash(energyMap, i + 1, j + 1, flashedMap, rowLen, colLen);
        condFlash(energyMap, i + 1, j, flashedMap, rowLen, colLen);
        condFlash(energyMap, i + 1, j - 1, flashedMap, rowLen, colLen);
        condFlash(energyMap, i, j - 1, flashedMap, rowLen, colLen);
        condFlash(energyMap, i - 1, j - 1, flashedMap, rowLen, colLen);
    }

    private static void condFlash(int[][] energyMap, int i, int j, int[][] flashedMap, int rowLen, int colLen) {
        if (outOfBounds(i, j, rowLen, colLen)) {
            return;
        }

        energyMap[i][j]++;
        if (energyMap[i][j] > 9) {
            flash(energyMap, i, j, flashedMap);
        }
    }

    private static boolean outOfBounds(int i, int j, int rowLength, int columnLength) {
        return i < 0 || i > rowLength - 1 // check vertical bounds
                || j < 0 || j > columnLength - 1; // Check horizontal bounds
    }

    private static void printFlashedMap(int[][] flashedMap) {
        for (int i = 0; i < flashedMap.length; i++) {
            for (int j = 0; j < flashedMap[0].length; j++) {
                if (flashedMap[i][j] == 1) {
                    System.out.print('*');
                } else {
                    System.out.print('.');
                }
            }
            System.out.print("\n");
        }
    }

    private static void printEnergyMap(int[][] heightMap) {

        int columns = heightMap[0].length;
        for (int[] ints : heightMap) {
            for (int j = 0; j < columns; j++) {
                System.out.print(ints[j]);
            }
            System.out.print("\n");
        }
    }
}
