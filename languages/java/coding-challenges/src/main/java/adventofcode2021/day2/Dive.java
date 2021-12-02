package adventofcode2021.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Dive {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day2/puzzleInput.txt");
        List<String> commands = Files.readAllLines(puzzleInputPath);

        int result = dive(commands);
        System.out.println("Depth value for puzzle input: " + result);

        int resultAim = diveAim(commands);
        System.out.println("Depth Aim value for puzzle input: " + resultAim);
    }

    public static int dive(List<String> commands) {
        int horizontalPosition = 0;
        int depth = 0;

        for (String command : commands) {
            String[] inputs = command.split(" ");
            String direction = inputs[0];
            int distance = Integer.valueOf(inputs[1]);

            if ("forward".equals(direction)) {
                horizontalPosition += distance;
            } else if ("down".equals(direction)) {
                depth += distance;
            } else if ("up".equals(direction)) {
                depth -= distance;
            }
        }
        return horizontalPosition * depth;
    }

    public static int diveAim(List<String> commands) {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        for (String command : commands) {
            String[] inputs = command.split(" ");
            String direction = inputs[0];
            int distance = Integer.valueOf(inputs[1]);

            if ("forward".equals(direction)) {
                horizontalPosition += distance;
                depth += (aim * distance);
            } else if ("down".equals(direction)) {
                aim += distance;
            } else if ("up".equals(direction)) {
                aim -= distance;
            }
        }
        return horizontalPosition * depth;
    }
}
