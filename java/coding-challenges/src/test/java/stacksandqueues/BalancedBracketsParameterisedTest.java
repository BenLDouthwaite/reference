package stacksandqueues;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class BalancedBracketsParameterisedTest {

    private String input;
    private String expectedResult;

    public BalancedBracketsParameterisedTest(String input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters()
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"{(([])[])[]]}", "NO"},
                {"{(([])[])[]}[]", "YES"},
                {"}][}}(}][))]", "NO"},
                {"[](){()}", "YES"},
                {"()", "YES"},
                {"({}([][]))[]()", "YES"},
                {"{)[](}]}]}))}(())(", "NO"},
                {"([[)", "NO"},
                {"))", "NO"},
        });
    }

    @Test
    public void isBalanced() {
        // given
        System.out.println("Given: " + input + ", expecting " + expectedResult);

        // when
        String result = BalancedBrackets.isBalanced(input);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }


}