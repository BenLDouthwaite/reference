package interview.starter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class StarterParameterisedTest {

    private Starter starter;

    private int input;
    private int result;

    public StarterParameterisedTest(int input, int result) {
        this.input = input;
        this.result = result;
    }

    @Parameterized.Parameters()
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {11, 12},
                {0, 1},
                {-10, -9},
        });
    }

    @Before
    public void setUp() throws Exception {
        starter = new Starter();
    }

    @Test
    public void testMethod() {
        assertThat(starter.testMethod(input)).isEqualTo(result);
    }
}