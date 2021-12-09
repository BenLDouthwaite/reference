package adventofcode2021.day9;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class SmokeBasin {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day9/puzzleInput.txt");
        List<String> input = Files.readAllLines(puzzleInputPath);

        int result1 = lowPointRiskLevel(input);
        log.info("Part 1: Result for puzzle input: " + result1);

//        int result2 = lowPointRiskLevel(input);
//        log.info("Part 2: Result for puzzle input: " + result2);
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
                if (j >= 1 && heightMap[i][j - 1] <= pointToCheck) { // Check Left
                    continue;
                }
                if (j < columns - 1 && heightMap[i][j + 1] <= pointToCheck) { // Check Right
                    continue;
                }
                if (i >= 1 && heightMap[i - 1][j] <= pointToCheck) { // Check Up
                    continue;
                }
                if (i < rows - 1 && heightMap[i + 1][j] <= pointToCheck) { // Check Down
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
}
