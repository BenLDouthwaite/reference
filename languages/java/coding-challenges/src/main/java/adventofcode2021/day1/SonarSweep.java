package adventofcode2021.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class SonarSweep {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day1/puzzleInput.txt");
        List<Integer> depthReadings = Files.readAllLines(puzzleInputPath)
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        int largerMeasurements = sweep(depthReadings);
        System.out.println("Larger Measurements for puzzle input: " + largerMeasurements);

        int largerMeasurementsSliding = sweepSliding(depthReadings);
        System.out.println("Larger Measurements for puzzle input: Sliding window: " + largerMeasurementsSliding);
    }

    public static int sweepSliding(List<Integer> depthReadings) {
        if (depthReadings == null || depthReadings.size() < 2) {
            return 0;
        }

        int increments = 0;
        for (int i = 3; i < depthReadings.size(); i++) {
            int previousSlidingWindowTotal = depthReadings.get(i - 1) + depthReadings.get(i - 2) + depthReadings.get(i - 3);
            int newSlidingWindowTotal = depthReadings.get(i) + depthReadings.get(i - 1) + depthReadings.get(i - 2);
            if (newSlidingWindowTotal > previousSlidingWindowTotal) {
                increments++;
            }
        }
        return increments;
    }

    public static int sweep(List<Integer> depthReadings) {
        if (depthReadings == null || depthReadings.size() < 2) {
            return 0;
        }

        int increments = 0;
        for (int i = 1; i < depthReadings.size(); i++) {
            if (depthReadings.get(i) > depthReadings.get(i - 1)) {
                increments++;
            }
        }
        return increments;
    }
}