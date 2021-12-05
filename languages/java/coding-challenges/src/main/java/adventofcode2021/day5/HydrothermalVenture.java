package adventofcode2021.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class HydrothermalVenture {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day5/puzzleInput.txt");
        List<String> input = Files.readAllLines(puzzleInputPath);

        int result1 = overlappingPoints(input, false);
        System.out.println("Part 1: Result for puzzle input: " + result1);

        int result2 = overlappingPoints(input, true);
        System.out.println("Part 2: Result for puzzle input: " + result2);
    }

    public static final int VGS = 1000; // Max vent grid size, TODO Rename once all working

    public static int overlappingPoints(List<String> inputs, boolean includeDiagonals) {

        // could cycle over values to check max size, we already know it.
        int[][] ventsCount = new int[VGS][VGS];

        for (String input: inputs) {

            int[] vent = Arrays.stream(input.split(" -> "))
                    .map(point -> point.split(","))
                    .flatMap(Stream::of)
                    .map(Integer::parseInt)
                    .mapToInt(s -> s)
                    .toArray();
            int x1 = vent[0], y1 = vent[1], x2 = vent[2], y2 = vent[3];

            // vertical
            if (x1 == x2) {
                for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                    ventsCount[i][x1]++;
                }
                continue;
            }

            // horizontal
            if (y1 == y2) {
                for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i ++) { // Inclusive iteration
                    ventsCount[y1][i]++;
                }
                continue;
            }

            // If not horizontal or vertical, must be a diagonal.
            if (!includeDiagonals) {
                continue;
            }

            // Diagonal
            for (int i = x1, j = y1;
                 (x1 < x2) ? i <= x2 : i >= x2; // Row and column updates should be equal, on diagonal
                 i = (x1 < x2) ? i + 1 : i - 1, j = (y1 < y2) ? j + 1 : j - 1) {
                ventsCount[j][i]++;
            }
        }

        return (int) Stream.of(ventsCount)
                .flatMapToInt(Arrays::stream)
                .filter(i -> i > 1)
                .count();
    }

    private static void printVents(int[][] ventsCount) {
        System.out.println();
        for (int i = 0; i < VGS; i++) {
            for (int j = 0; j < VGS; j++) {
                if (ventsCount[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(ventsCount[i][j]);
                }
            }
            System.out.print("\n");
        }
    }
}
