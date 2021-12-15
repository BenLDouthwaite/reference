package interview.starter;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StarterTest {

    private Starter starter;

    @Before
    public void setUp() throws Exception {
        starter = new Starter();
    }

    @Test
    public void testMethod() {
        // given
        int input = 11;

        // when
        int result = starter.testMethod(input);

        // then
        assertThat(result).isEqualTo(12);
    }
}