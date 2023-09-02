package adventofcode2021.day12;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Slf4j
public class PassagePathing {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day11/puzzleInput.txt");
        List<String> input = Files.readAllLines(puzzleInputPath);

        int result1 = pathCount(input);
        log.info("Part 1: Result for puzzle input: " + result1);

        long result2 = pathCount(input);
        log.info("Part 2: Result for puzzle input: " + result2);
    }

    public static int pathCount(List<String> input) {
        return 0;
    }
}
