package adventofcode2021.day7;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class TrecheryOfWhales {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day7/puzzleInput.txt");
        String input = Files.readAllLines(puzzleInputPath).get(0);

        int result1 = crabAlignment(input, false);
        log.info("Part 1: Result for puzzle input: " + result1);

        int result2 = crabAlignment(input, true);
        log.info("Part 2: Result for puzzle input: " + result2);
    }

    public static int crabAlignment(String input, boolean badFuel) {
        List<Integer> startingCrabLocations = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .sorted()
                .collect(Collectors.toList());
        int max = startingCrabLocations.get(startingCrabLocations.size() - 1);

        Map<Integer, Long> startingLocationCount = startingCrabLocations.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));

        int totalFuelAtCheapest = Integer.MAX_VALUE;
        for (int i = 0; i < max; i++) {
            int totalFuel = getRequiredFuelForDestination(startingLocationCount, i, badFuel);
            if (totalFuel < totalFuelAtCheapest) {
                totalFuelAtCheapest = totalFuel;
            } else {
                break; // Once it gets more expensive, pattern will continue so exit early
            }
        }
        return totalFuelAtCheapest;
    }

    private static int getRequiredFuelForDestination(
            Map<Integer, Long> startingLocationCountMap,
            int desiredPosition,
            boolean badFuel) {

        int totalFuel = 0;
        for (Map.Entry<Integer, Long> startingLocationCount: startingLocationCountMap.entrySet()) {
            int startLoc = startingLocationCount.getKey();
            long locCount = startingLocationCount.getValue();
            int diff = Math.abs(startLoc - desiredPosition);
            if (badFuel) { // pt2
                int tnDiff = (diff * (diff + 1)) / 2;
                totalFuel += (tnDiff * locCount);
            } else { // pt1
                totalFuel += (diff * locCount);
            }
        }

        return totalFuel;
    }

}
