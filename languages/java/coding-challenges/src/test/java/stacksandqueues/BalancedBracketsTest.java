package stacksandqueues;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BalancedBracketsTest {

    @Test
    public void isBalanced() throws IOException {
        // given
        Path inputPath = Path.of("./src/test/java/stacksandqueues/testInput.txt");
        List<String> inputs = Files.readAllLines(inputPath);

        Path expectedResultPath = Path.of("./src/test/java/stacksandqueues/expectedTestResults.txt");
        List<String> expectedResults = Files.readAllLines(expectedResultPath);

        inputs.remove(0); // First line is just the number of test cases.

        // Check test setup
        assertThat(inputs.size()).isEqualTo(expectedResults.size());

        // when
        for (int i  = 0; i < inputs.size(); i++) {

            String input = inputs.get(i);
            String expected = expectedResults.get(i);

            String result = BalancedBrackets.isBalanced(input);

            System.out.println("Testing. Input : " + input + ", expected : " + expected + ", result: " + result);

            // then
            assertThat(result).isEqualTo(expected);
        }

    }


}