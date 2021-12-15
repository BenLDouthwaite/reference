package adventofcode2021.day9;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Slf4j
public class SmokeBasin {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day9/puzzleInput.txt");
        List<String> input = Files.readAllLines(puzzleInputPath);

        int result1 = lowPointRiskLevel(input);
        log.info("Part 1: Result for puzzle input: " + result1);

        int result2 = basinSize(input);
        log.info("Part 2: Result for puzzle input: " + result2);
    }

    public static int basinSize(List<String> input) {

        int rows = input.size();
        int columns = input.get(0).length();
        int[][] heightMap = new int[rows][columns];
        for (int i = 0 ; i < rows; i++) {
            int[] inputNums = Arrays.stream(input.get(i).split(""))
                    .mapToInt(Integer::valueOf)
                    .toArray();
            heightMap[i] = inputNums;
        }

        printMap(heightMap);

        List<Integer> basinSizes = new ArrayList<>();

        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < columns; j++) {
                int pointToCheck = heightMap[i][j];
                if (isLowestPoint(rows, columns, heightMap, i, j, pointToCheck)) continue;

                log.info("Check for basin size at : i {}, j {}", i, j);
                Set<Pair> basinCoordinates = calculateBasinSize(heightMap, i, j, new HashSet<>());

                printBasinCoordinates(heightMap, basinCoordinates);

                basinSizes.add(basinCoordinates.size());
            }
        }

        return basinSizes.stream()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .reduce(1, (a, b) -> a * b);
    }

    private static boolean isLowestPoint(int rows, int columns, int[][] heightMap, int i, int j, int pointToCheck) {
        if (j >= 1 && heightMap[i][j - 1] <= pointToCheck) { // Check Left
            return true;
        }
        if (j < columns - 1 && heightMap[i][j + 1] <= pointToCheck) { // Check Right
            return true;
        }
        if (i >= 1 && heightMap[i - 1][j] <= pointToCheck) { // Check Up
            return true;
        }
        if (i < rows - 1 && heightMap[i + 1][j] <= pointToCheck) { // Check Down
            return true;
        }
        return false;
    }

    private static Set<Pair> calculateBasinSize(
            int[][] heightMap,
            int i,
            int j,
            Set<Pair> basinCoordinates) {
        int pointToCheck = heightMap[i][j];

        if (pointToCheck == 9) {
            return Set.of();
        }

        Pair startCoordinates = Pair.of(i, j);
        if (basinCoordinates.contains(startCoordinates)) {
            return Set.of(); // Already traversed this path, no need to again
        }

        basinCoordinates.add(startCoordinates);

        if (j >= 1 && pointToCheck <= heightMap[i][j - 1]) { // Check Left
            basinCoordinates.addAll(calculateBasinSize(heightMap, i, j - 1, basinCoordinates));
        }

        if (j < heightMap[0].length - 1 && pointToCheck <= heightMap[i][j + 1]) { // Check right
            basinCoordinates.addAll(calculateBasinSize(heightMap, i, j + 1, basinCoordinates));
        }

        if (i >= 1  && pointToCheck <= heightMap[i - 1][j]) { // Check up
            basinCoordinates.addAll(calculateBasinSize(heightMap, i - 1, j, basinCoordinates));
        }

        if (i < heightMap.length - 1 && pointToCheck <= heightMap[i + 1][j]) { // Check Down
            basinCoordinates.addAll(calculateBasinSize(heightMap, i + 1, j, basinCoordinates));
        }


        return basinCoordinates;
    }

    private static void printBasinCoordinates(int[][] heightMap, Set<Pair> basinCoordinates) {
        int columns = heightMap[0].length;
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < columns; j++) {
                if (basinCoordinates.contains(Pair.of(i, j))) {
                    System.out.print(heightMap[i][j]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
    }

    public static int lowPointRiskLevel(List<String> input) {

        int rows = input.size();
        int columns = input.get(0).length();
        int[][] heightMap = new int[rows][columns];
        for (int i = 0 ; i < rows; i++) {
            int[] inputNums = Arrays.stream(input.get(i).split(""))
                    .mapToInt(Integer::valueOf)
                    .toArray();
            heightMap[i] = inputNums;
        }

        printMap(heightMap);

        // Find Low points -> Try looking in the mirror! ha.

        int lowPointRiskLevel = 0;
        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < columns; j++) {
                int pointToCheck = heightMap[i][j];
                if (isLowestPoint(rows, columns, heightMap, i, j, pointToCheck)) {
                    continue;
                }
                lowPointRiskLevel += (pointToCheck + 1);
            }
        }
        return lowPointRiskLevel;
    }

    private static void printMap(int[][] heightMap) {

        int columns = heightMap[0].length;
        for (int[] ints : heightMap) {
            for (int j = 0; j < columns; j++) {
                System.out.print(ints[j]);
            }
            System.out.print("\n");
        }
    }

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    private static class Pair {
        Integer x;
        Integer y;

        public static Pair of(Integer x, Integer y) {
            return new Pair(x, y);
        }
    }
}
